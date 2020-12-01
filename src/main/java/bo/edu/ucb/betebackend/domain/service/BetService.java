package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Bet;
import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.dto.BetRequest;
import bo.edu.ucb.betebackend.domain.repository.IBetRepository;
import bo.edu.ucb.betebackend.domain.repository.IGamblerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BetService {
    final private IBetRepository betRepository;
    final private IGamblerRepository gamblerRepository;

    public BetService(IBetRepository betRepository, IGamblerRepository gamblerRepository) {
        this.betRepository = betRepository;
        this.gamblerRepository = gamblerRepository;
    }

    public Optional<List<Bet>> getAllBet() {
        return betRepository.getAllBets();
    }

    public Optional<Bet> getBetById(Integer id) {
        return betRepository.getBetById(id);
    }

    public Bet saveBet(Match match, Gambler gambler, Team team, BetRequest bet) {
        gambler.setCoins(gambler.getCoins() - bet.getQuantity());
        Gambler saveGambler = gamblerRepository.saveGambler(gambler);
        Bet newBet = getNewBet(match, saveGambler, team, bet);
        return betRepository.saveBet(newBet);
    }

    private Bet getNewBet(Match match, Gambler gambler, Team team, BetRequest bet) {
        Bet newBet = new Bet();
        newBet.setDate(new Date());
        newBet.setGambler(gambler);
        newBet.setTeamIdTeam(team);
        newBet.setMatch(match);
        newBet.setQuantity(bet.getQuantity());
        newBet.setTeam(bet.getTeam());
        return newBet;
    }

    public boolean removeBet(Integer id) {
        return getBetById(id).map(bet -> extracted(id)).orElse(false);
    }

    private boolean extracted(Integer id) {
        betRepository.removeBet(id);
        return true;
    }
}
