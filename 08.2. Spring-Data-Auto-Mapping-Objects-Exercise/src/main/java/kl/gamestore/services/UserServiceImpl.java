package kl.gamestore.services;

import kl.gamestore.domain.dtos.user.UserLoginDto;
import kl.gamestore.domain.dtos.user.UserRegisterDto;
import kl.gamestore.domain.entities.Game;
import kl.gamestore.domain.entities.Role;
import kl.gamestore.domain.entities.User;
import kl.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private Validator validator;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();

        Set<ConstraintViolation<UserRegisterDto>> violations = this.validator.validate(userRegisterDto);

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            sb.append("Passwords don't match!").append(System.lineSeparator());
        }

        if (violations.size() > 0) {
            for (ConstraintViolation<UserRegisterDto> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            User user = this.modelMapper.map(userRegisterDto, User.class);

            if (this.userRepository.findAll().isEmpty()) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
            }

            User userFromDb = this.findByEmail(userRegisterDto.getEmail());

            if (userFromDb == null) {
                try {
                    User savedUser = this.userRepository.saveAndFlush(user);
                    if (savedUser != null) {
                        sb
                                .append(String.format("%s was registered successfully!", savedUser.getFullName()))
                                .append(System.lineSeparator());
                    }
                } catch (Exception e) {
                    sb.append(e.getMessage()).append(System.lineSeparator());
                }
            } else {
                sb.append("User already exists!").append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        StringBuilder sb = new StringBuilder();

        Set<ConstraintViolation<UserLoginDto>> violations = this.validator.validate(userLoginDto);

        User userFromDb = this.findByEmail(userLoginDto.getEmail());

        if (userFromDb == null) {
            sb.append("Incorrect email or password!").append(System.lineSeparator());
        }else if (!userLoginDto.getPassword().equals(userFromDb.getPassword())) {
            sb.append("Incorrect email or password!").append(System.lineSeparator());
        }else{
            if (violations.size() > 0) {
                for (ConstraintViolation<UserLoginDto> violation : violations) {
                    sb.append(violation.getMessage()).append(System.lineSeparator());
                }
            } else {
                sb.append(String.format("Successfully logged in %s", userFromDb.getFullName())).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public String getBoughtGames(String email) {
//        StringBuilder sb = new StringBuilder();
//        User userByEmail = this.userRepository.findByEmail(email);
//        if(userByEmail != null){
////            userByEmail.getGames().forEach(g-> System.out.println(g.getTitle()));
////                    sb.append(g.getTitle())
////                    .append(System.lineSeparator()));
//            //                System.out.println(game.getTitle());
//            Set<Game> gameSet = new HashSet<>(userByEmail.getGames());
//            if(userByEmail.getGames().size() > 0){
//                return sb.toString().trim();
//            }
//            return String.format("%s has not bought any game!", userByEmail.getFullName());
//        }
        return "Please log In to see your bought games!";

    }

    @Override
    public String addGames(Long id, Set<Game> gameSet) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Successfully bought games:").append(System.lineSeparator());
//
//        User user = this.userRepository.findById(id).orElse(null);
//
//        if(user != null && gameSet.size() > 0){
//            Set<Game> uniqueGames = new HashSet<>(user.getGames());
//            for (Game game : gameSet) {
//                uniqueGames.add(game);
//                sb.append(String.format(" -%s", game.getTitle())).append(System.lineSeparator());
//            }
//            user.setGames(uniqueGames);
//
//            User savedUser = this.userRepository.saveAndFlush(user);
//            if(savedUser != null){
//                return sb.toString();
//            }
//        }else {
//            return "No games were bought!";
//        }

        return null;
    }

}
