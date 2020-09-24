package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.domain.repository.IGameRepository;
import bo.edu.ucb.betebackend.persistence.dao.GameRepository;
import bo.edu.ucb.betebackend.persistence.entity.GameEntity;
import bo.edu.ucb.betebackend.persistence.mapper.GameMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GameDao implements IGameRepository {
    final private GameRepository gameRepository;
    final private GameMapper gameMapper;

    public GameDao(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public Optional<List<Game>> getAllGames() {
        List<GameEntity> gameEntities = gameRepository.findAll();
        return Optional.ofNullable(gameMapper.toGameList(gameEntities));
    }

    @Override
    public Optional<Game> getGameById(Integer id) {
        return gameRepository.findById(id).map(gameMapper::toGame);
    }

    @Override
    public Game saveGame(Game game) {
        return gameMapper.toGame(gameRepository.save(gameMapper.toGameEntity(game)));
    }

    @Override
    public void removeGame(Integer id) {
        gameRepository.deleteById(id);
    }
}
