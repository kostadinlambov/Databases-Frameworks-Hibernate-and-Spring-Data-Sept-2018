package kl.gamestore.services;

import kl.gamestore.domain.dtos.game.GameCreateDto;
import kl.gamestore.domain.entities.Game;

import java.util.Set;

public interface GameService {
    String addGame(GameCreateDto gameCreateDto);
    Game findByTitle(String title);

    String editGame(String[] tokens);

    String deleteGame(Long id);

    String getAllGames();

    String details(String title);

    String addGames(Long id, Set<Game> gameSet);

    String getBoughtGames(String loggedInUser);
}
