package kl.gamestore.services;

import kl.gamestore.domain.dtos.game.GameCreateDto;
import kl.gamestore.domain.dtos.game.GameEditDto;
import kl.gamestore.domain.entities.Game;
import kl.gamestore.domain.entities.User;
import kl.gamestore.repositories.GameRepository;
import kl.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private Validator validator;


    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public String addGame(GameCreateDto gameCreateDto) {
        Set<ConstraintViolation<GameCreateDto>> violations = this.validator.validate(gameCreateDto);

        if (violations.size() > 0) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<GameCreateDto> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
            return sb.toString().trim();
        } else {
            Game gameByTitle = this.findByTitle(gameCreateDto.getTitle());
            if (gameByTitle != null) {
                return "Game already exist!";
            }

            Game game = this.modelMapper.map(gameCreateDto, Game.class);
            Game savedGame = this.gameRepository.saveAndFlush(game);

            if (savedGame != null) {
                return String.format("Added %s", savedGame.getTitle());
            }
        }
        return null;
    }


    @Override
    public String editGame(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        String id = tokens[1];

        Game game = this.gameRepository.findById(Long.parseLong(id)).orElse(null);
        if (game == null) {
            return "Game not found!";
        }

        GameEditDto gameToEdit = modelMapper.map(game, GameEditDto.class);
        gameToEdit.setId(Long.parseLong(id));

        for (int i = 2; i < tokens.length; i++) {
            String[] propertiesElements = tokens[i].split("=");
            String propertyName = propertiesElements[0];
            String propertyValue = propertiesElements[1];
            switch (propertyName) {
                case "title":
                    return "Title cannot be edited!";
                case "price":
                    gameToEdit.setPrice(new BigDecimal(propertyValue));
                    break;
                case "size":
                    gameToEdit.setSize(new BigDecimal(propertyValue));
                    break;
                case "trailer":
                    gameToEdit.setTrailer(propertyValue);
                    break;
                case "thumbnailUrl":
                    gameToEdit.setThumbnailUrl(propertyValue);
                    break;
                case "description":
                    gameToEdit.setDescription(propertyValue);
                    break;
            }
        }

        Set<ConstraintViolation<GameEditDto>> violations = this.validator.validate(gameToEdit);

        if (violations.size() > 0) {
            for (ConstraintViolation<GameEditDto> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());

            }
            return sb.toString().trim();
        } else {
            Game editedGame = this.modelMapper.map(gameToEdit, Game.class);
            if (editedGame != null) {
                Game savedGame = this.gameRepository.saveAndFlush(editedGame);
                if (savedGame != null) {
                    return String.format("Edited %s", savedGame.getTitle());
                }
            }
        }

        return null;
    }

    @Override
    public String deleteGame(Long id) {
        Game game = this.gameRepository.findById(id).orElse(null);
        if (game != null) {
            String title = game.getTitle();
            this.gameRepository.deleteById(id);
            return String.format("Deleted %s", title);
        }
        return "Game not found!";
    }

    @Override
    public String getAllGames() {
        StringBuilder sb = new StringBuilder();
        List<Game> all = this.gameRepository.findAll();

        if (all.size() > 0) {
            all.forEach(game -> sb
                    .append(String.format("%s %s", game.getTitle(), game.getPrice()))
                    .append(System.lineSeparator()));
        } else {
            sb.append("No games found!");
        }

        return sb.toString().trim();
    }

    @Override
    public String details(String title) {
        Game gameByTitle = this.findByTitle(title);
        if (gameByTitle == null) {
            return "Game not found!";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Title: " + gameByTitle.getTitle())
                .append(System.lineSeparator())
                .append("Price: " + gameByTitle.getPrice())
                .append(System.lineSeparator())
                .append("Description : " + gameByTitle.getDescription())
                .append(System.lineSeparator())
                .append("Release date : " + gameByTitle.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyy")))
                .append(System.lineSeparator());

        return sb.toString().trim();
    }

    @Override
    public String addGames(Long id, Set<Game> gameSet) {
        StringBuilder sb = new StringBuilder();

        User user = this.userRepository
                .findById(id)
                .orElse(null);


        if (user != null ) {
            if(gameSet.size() > 0){
                sb.append("Successfully bought games:").append(System.lineSeparator());

                for (Game game : gameSet) {
                    Game gameById = this.gameRepository
                            .findById(game.getId())
                            .orElse(null);

                    if (gameById != null) {
                        gameById.getBuyers().add(user);
                        sb.append(String.format(" -%s", gameById.getTitle())).append(System.lineSeparator());

                        try {
                            this.gameRepository.saveAndFlush(gameById);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }else {
                sb.append("Shopping cart is empty!");
            }

            return sb.toString().trim();

        } else {
            return "No games were bought!";
        }

    }

    @Override
    public String getBoughtGames(String email) {
        StringBuilder sb = new StringBuilder();

        User user = this.userRepository.findByEmail(email);

        if (user != null) {
            this.gameRepository.findAll().forEach(game -> {
                if (game.getBuyers().contains(user)){
                    sb.append(game.getTitle()).append(System.lineSeparator());
                }
            });

            if(sb.length() == 0){
                sb.append(String.format("%s has not bought any game!", user.getFullName()));
            }
            return sb.toString().trim();
        }

        return "Please login!";
    }

    @Override
    public Game findByTitle(String title) {
        return this.gameRepository.findByTitle(title);
    }
}
