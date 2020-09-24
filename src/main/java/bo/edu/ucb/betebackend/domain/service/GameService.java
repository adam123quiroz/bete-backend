package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.domain.repository.IGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    final private IGameRepository gameRepository;

    public GameService(IGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Optional<List<Game>> getAllGame() {
        return gameRepository.getAllGames();
    }

    public Optional<Game> getGameById(Integer id) {
        return gameRepository.getGameById(id);
    }

    public Game saveGame(Game game) {
        return gameRepository.saveGame(game);
    }

    public boolean removeGame(Integer id) {
        return getGameById(id).map(game -> {
            gameRepository.removeGame(id);
            return true;
        }).orElse(false);
    }
}
