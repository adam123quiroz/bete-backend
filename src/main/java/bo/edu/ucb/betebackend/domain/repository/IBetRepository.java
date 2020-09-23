package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.Bet;

import java.util.List;
import java.util.Optional;

public interface IBetRepository {
    Optional<List<Bet>> getAllBets();
    Optional<Bet> getBetById(Integer id);
    Bet saveBet(Bet bet);
    void removeBet(Integer id);
}
