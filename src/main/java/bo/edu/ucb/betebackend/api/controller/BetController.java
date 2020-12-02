package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.TeamNotFoundException;
import bo.edu.ucb.betebackend.domain.Bet;
import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.dto.BetRequest;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import bo.edu.ucb.betebackend.domain.service.BetService;
import bo.edu.ucb.betebackend.domain.service.GamblerService;
import bo.edu.ucb.betebackend.domain.service.MatchService;
import bo.edu.ucb.betebackend.domain.service.TeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/bet")
public class BetController {
    final private BetService betService;
    final private MatchService matchService;
    final private TeamService teamService;
    final private GamblerService gamblerService;

    public BetController(BetService betService, MatchService matchService, TeamService teamService, GamblerService gamblerService) {
        this.betService = betService;
        this.matchService = matchService;
        this.teamService = teamService;
        this.gamblerService = gamblerService;
    }

    @CrossOrigin
    @GetMapping("/all")
    @ApiOperation("Obtain a list of all bets")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public List<Bet> getAllBets() {
        return betService.getAllBet().orElseGet(Collections::emptyList);
    }

    public Optional<Bet> getBet(Integer id) {
        return betService.getBetById(id);
    }

    @CrossOrigin
    @PostMapping("/create")
    @ApiOperation("Create a bet for a match")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> saveBet(@RequestBody BetRequest bet) throws Exception {
        Integer idMatch = bet.getMatchId(), idGambler = bet.getGamblerId(), idTeam = bet.getTeamIdTeam();
        Match match = matchService.getMatchById(idMatch)
                .orElseThrow(Exception::new); // TODO: 12/1/2020 Create a New Exception Match
        Gambler gambler = gamblerService.getGamblerById(idGambler)
                .orElseThrow(Exception::new); // TODO: 12/1/2020 Create a New Exception Gambler
        Team team = teamService.getTeamById(idTeam)
                .orElseThrow(() -> new TeamNotFoundException(idTeam));
        Bet newBet = betService.saveBet(match, gambler, team, bet);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(newBet));
    }

    public boolean delete(Integer id) {
        return betService.removeBet(id);
    }
}
