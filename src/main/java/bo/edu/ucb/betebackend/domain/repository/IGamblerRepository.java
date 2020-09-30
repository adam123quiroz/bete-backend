package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.Gambler;

import java.util.List;
import java.util.Optional;

public interface IGamblerRepository {
    Optional<List<Gambler>> getAllGamblers();
    Optional<Gambler> getGamblerById(Integer id);
    Gambler saveGambler(Gambler game);
    void removeGambler(Integer id);
}
