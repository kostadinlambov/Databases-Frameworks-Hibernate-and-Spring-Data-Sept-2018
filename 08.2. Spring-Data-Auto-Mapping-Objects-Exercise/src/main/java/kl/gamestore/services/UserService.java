package kl.gamestore.services;

import kl.gamestore.domain.dtos.user.UserLoginDto;
import kl.gamestore.domain.dtos.user.UserRegisterDto;
import kl.gamestore.domain.entities.Game;
import kl.gamestore.domain.entities.User;

import java.util.Set;

public interface UserService  {
    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    User findByEmail(String email);

    String getBoughtGames(String email);

    String addGames(Long id, Set<Game> gameSet);
}
