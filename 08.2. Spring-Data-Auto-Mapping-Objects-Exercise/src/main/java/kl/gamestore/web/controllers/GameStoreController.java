package kl.gamestore.web.controllers;

import kl.gamestore.domain.dtos.order.OrderAddItemDto;
import kl.gamestore.domain.dtos.order.OrderRemoveItemDto;
import kl.gamestore.domain.dtos.game.GameCreateDto;
import kl.gamestore.domain.dtos.user.UserLoginDto;
import kl.gamestore.domain.dtos.user.UserRegisterDto;
import kl.gamestore.domain.entities.User;
import kl.gamestore.services.GameService;
import kl.gamestore.services.OrderService;
import kl.gamestore.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class GameStoreController implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;
    private final OrderService orderService;
    private final BufferedReader reader;

    private String loggedInUser;

    public GameStoreController(UserService userService, BufferedReader reader, GameService gameService, OrderService orderService) {
        this.userService = userService;
        this.reader = reader;
        this.gameService = gameService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            String[] tokens = this.reader.readLine().split("\\|");

            switch (tokens[0]) {
                case "RegisterUser":
                    UserRegisterDto userRegisterDto = new UserRegisterDto(tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim());
                    System.out.println(this.userService.registerUser(userRegisterDto));
                    break;
                case "LoginUser":
                    if (this.loggedInUser == null) {
                        UserLoginDto userLoginDto = new UserLoginDto(tokens[1].trim(), tokens[2].trim());
                        String response = this.userService.loginUser(userLoginDto);
                        if(response.contains("Successfully logged in")){
                            this.loggedInUser = userLoginDto.getEmail();
                        }
                        System.out.println(response);
                    } else {
                        System.out.println("Session is taken!");
                    }
                    break;
                case "Logout":
                    if (this.loggedInUser == null) {
                        System.out.println("Cannot log out. No user was logged in. Please login!");
                    } else {
                        User userByEmail = this.userService.findByEmail(this.loggedInUser);
                        this.loggedInUser = null;
                        System.out.printf("User %s successfully logged out\n", userByEmail.getFullName());
                    }
                    break;
                case "AddGame":
                    LocalDate releaseDate = LocalDate.parse(tokens[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    GameCreateDto gameCreateDto =
                            new GameCreateDto(tokens[1], new BigDecimal(tokens[2]),
                                    new BigDecimal(tokens[3]), tokens[4], tokens[5], tokens[6], releaseDate);

                    System.out.println(this.gameService.addGame(gameCreateDto));
                    break;
                case "EditGame":
                    System.out.println(this.gameService.editGame(tokens));
                    break;
                case "DeleteGame":
                    Long id = Long.parseLong(tokens[1]);
                    System.out.println(this.gameService.deleteGame(id));
                    break;
                case "AllGame":
                    System.out.println(this.gameService.getAllGames());
                    break;
                case "DetailGame":
                    System.out.println(this.gameService.details(tokens[1]));
                    break;
                case "OwnedGame":
                    if(this.loggedInUser != null){
                        System.out.println(this.gameService.getBoughtGames(this.loggedInUser));
                    }else{
                        System.out.println("Please login!");
                    }
                    break;
                case "AddItem":
                    if(this.loggedInUser != null){
                        String gameTitle = tokens[1];
                        OrderAddItemDto orderAddItemDto = new OrderAddItemDto(gameTitle);
                        System.out.println(this.orderService.addItem(orderAddItemDto, this.loggedInUser));
                    }else{
                        System.out.println("Please login!");
                    }
                    break;
                case "RemoveItem":
                    if(this.loggedInUser != null){
                        String gameTitleToRemove = tokens[1];
                        OrderRemoveItemDto orderRemoveItemDto = new OrderRemoveItemDto(gameTitleToRemove);

                        System.out.println(this.orderService.removeItem(orderRemoveItemDto, this.loggedInUser));
                    }else{
                        System.out.println("Please login!");
                    }
                    break;
                case "BuyItem":
                    if(this.loggedInUser != null){
                        System.out.println(this.orderService.buyItems(this.loggedInUser));
                    }else{
                        System.out.println("Please login!");
                    }
                    break;
                default:
                    System.out.println("Wrong input format!");
                    break;
            }
        }
    }
}
