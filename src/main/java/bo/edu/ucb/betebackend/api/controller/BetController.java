package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Bet;
import bo.edu.ucb.betebackend.domain.service.BetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bet")
public class BetController {
    final private BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    public List<Bet> getAllBets() {
        return betService.getAllBet().orElseGet(Collections::emptyList);
    }

    public Optional<Bet> getBet(Integer id) {
        return betService.getBetById(id);
    }

    public Bet save(Bet bet) {
        return betService.saveBet(bet);
    }

    public boolean delete(Integer id) {
        return betService.removeBet(id);
    }
}
