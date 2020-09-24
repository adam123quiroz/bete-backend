package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.Game;

import java.util.List;
import java.util.Optional;

public interface IGameRepository {
    Optional<List<Game>> getAllGames();
    Optional<Game> getGameById(Integer id);
    Game saveGame(Game game);
    void removeGame(Integer id);
}
