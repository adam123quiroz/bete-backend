package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Bet;
import bo.edu.ucb.betebackend.domain.repository.IBetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BetService {
    final private IBetRepository betRepository;

    public BetService(IBetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public Optional<List<Bet>> getAllBet() {
        return betRepository.getAllBets();
    }

    public Optional<Bet> getBetById(Integer id) {
        return betRepository.getBetById(id);
    }

    public Bet saveBet(Bet bet) {
        return betRepository.saveBet(bet);
    }

    public boolean removeBet(Integer id) {
       return getBetById(id).map(bet -> {
           betRepository.removeBet(id);
           return true;
       }).orElse(false);
    }
}
