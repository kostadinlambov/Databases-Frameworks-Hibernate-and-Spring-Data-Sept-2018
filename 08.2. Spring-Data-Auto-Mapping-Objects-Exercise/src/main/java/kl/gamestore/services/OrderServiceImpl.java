package kl.gamestore.services;

import kl.gamestore.domain.dtos.order.OrderAddItemDto;
import kl.gamestore.domain.dtos.order.OrderRemoveItemDto;
import kl.gamestore.domain.entities.Game;
import kl.gamestore.domain.entities.Order;
import kl.gamestore.domain.entities.User;
import kl.gamestore.repositories.GameRepository;
import kl.gamestore.repositories.OrderRepository;
import kl.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final GameService gameService;
    private final ModelMapper modelMapper;
    private Validator validator;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, Validator validator, GameRepository gameRepository, UserRepository userRepository, UserService userService, GameService gameService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.gameService = gameService;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public String addItem(OrderAddItemDto orderAddItemDto, String usersEmail) {
        Game gameByTitle = this.gameRepository.findByTitle(orderAddItemDto.getGameTitle());
        if (gameByTitle == null) {
            return "Game not found!";
        }

        User user = this.userRepository.findByEmail(usersEmail);
        if (user == null) {
            return "Please log In to be able to adding items to your shopping cart!";
        }

        if(gameByTitle.getBuyers().contains(user)){
            return  String.format("%s already owns %s", user.getFullName(), gameByTitle.getTitle());
        }

        Order order = new Order();
        order.setUser(user);
        order.setGame(gameByTitle);

        Order savedOrder = this.orderRepository.saveAndFlush(order);
        if (savedOrder != null) {
            return String.format("%s added to cart.", gameByTitle.getTitle());
        }

        return null;
    }

    @Override
    public String removeItem(OrderRemoveItemDto orderRemoveItemDto, String usersEmail) {
        Game gameByTitle = this.gameRepository.findByTitle(orderRemoveItemDto.getGameTitle());
        if (gameByTitle == null) {
            return "Game not found!";
        }

        User user = this.userRepository.findByEmail(usersEmail);
        if (user == null) {
            return "Please log In to be able to adding items to your shopping cart!";
        }

        try {
            List<Order> allByUserIdAndGameId = this.findAllByUserIdAndGameId(user.getId(), gameByTitle.getId());
            if (allByUserIdAndGameId.size() > 0) {
                allByUserIdAndGameId.stream()
                        .forEach(this.orderRepository::delete);
                return String.format("%s removed from cart.", orderRemoveItemDto.getGameTitle());
            } else {
                return String.format("%s is not in your shopping cart.", orderRemoveItemDto.getGameTitle());
            }


        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Order> findAllByUserIdAndGameId(Long userId, Long gameId) {
        return this.orderRepository.findAllByUserIdAndGameId(userId, gameId);
    }

    @Override
    public List<Order> findAllOrdersByUserId(Long userId) {
        return this.orderRepository.findAllByUserId(userId);
    }

    @Override
    public String buyItems(String usersEmail) {
        User userByEmail = this.userRepository.findByEmail(usersEmail);
        Set<Game> gameSet = new HashSet<>();
        try {
            List<Order> allOrdersByUserId = this.findAllOrdersByUserId(userByEmail.getId());
            allOrdersByUserId.forEach(order -> {
                gameSet.add(order.getGame());
            });

            String result = this.gameService.addGames(userByEmail.getId(), gameSet);

            allOrdersByUserId.forEach(order -> {
                this.orderRepository.deleteById(order.getId());
            });


            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "No games were bought!";
    }
}
