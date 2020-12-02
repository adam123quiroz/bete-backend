package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Bet;
import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.dto.request.BetRequest;
import bo.edu.ucb.betebackend.domain.repository.IBetRepository;
import bo.edu.ucb.betebackend.domain.repository.IGamblerRepository;
import bo.edu.ucb.betebackend.domain.repository.IMatchRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BetService {
    private final IBetRepository betRepository;
    private final IGamblerRepository gamblerRepository;
    private final IMatchRepository matchRepository;

    public BetService(IBetRepository betRepository, IGamblerRepository gamblerRepository, IMatchRepository matchRepository) {
        this.betRepository = betRepository;
        this.gamblerRepository = gamblerRepository;
        this.matchRepository = matchRepository;
    }

    public Optional<List<Bet>> getAllBet() {
        return betRepository.getAllBets();
    }

    public Optional<Bet> getBetById(Integer id) {
        return betRepository.getBetById(id);
    }

    public Optional<Bet> saveBet(Match match, Gambler gambler, Team team, BetRequest bet) {
        int total = gambler.getCoins() - bet.getQuantity();
        if (total <= 0) return Optional.empty();
        gambler.setCoins(total);
        Gambler saveGambler = gamblerRepository.saveGambler(gambler);
        Bet newBet = getNewBet(match, saveGambler, team, bet);
        return Optional.ofNullable(betRepository.saveBet(newBet));
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

    public Optional<List<Bet>> getAllBetByMatch(Match match) {
        return Optional.empty();
    }

    public boolean removeBet(Integer id) {
        return getBetById(id).map(bet -> extracted(id)).orElse(false);
    }

    private boolean extracted(Integer id) {
        betRepository.removeBet(id);
        return true;
    }
}
