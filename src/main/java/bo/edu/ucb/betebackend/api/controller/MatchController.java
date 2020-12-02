package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.TournamentNotFoundException;
import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.dto.response.FormatResponse;
import bo.edu.ucb.betebackend.domain.dto.response.MatchExpectResponse;
import bo.edu.ucb.betebackend.domain.service.MatchService;
import bo.edu.ucb.betebackend.domain.service.TeamService;
import bo.edu.ucb.betebackend.domain.service.TournamentService;
import bo.edu.ucb.betebackend.domain.utils.LeagueUtils;
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
@RequestMapping("/match")
public class MatchController {
    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final MatchService matchService;

    public MatchController(TeamService teamService, TournamentService tournamentService, MatchService matchService) {
        this.teamService = teamService;
        this.tournamentService = tournamentService;
        this.matchService = matchService;
    }

    @CrossOrigin
    @PostMapping("/{idTournament}/draw")
    @ApiOperation("Draw an entire tournamet creating matches between participants")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> raffleTeams(@PathVariable String idTournament)
            throws NumberFormatException {
        Integer idTournamentIntegerInteger = Integer.valueOf(idTournament);
        Tournament tournament = tournamentService.getTournamentById(idTournamentIntegerInteger)
                .orElseThrow(() -> new TournamentNotFoundException(idTournamentIntegerInteger));
        Optional<LeagueUtils.GroupMatch> groupMatch = matchService.raffleTeams(tournament);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(groupMatch));
    }

    @CrossOrigin
    @GetMapping("/tournament/{idTournament}")
    @ApiOperation("Get all info of a tournament by its id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> getMatch(@PathVariable String idTournament)
            throws NumberFormatException {
        Integer idTournamentIntegerInteger = Integer.valueOf(idTournament);
        Tournament tournament = tournamentService.getTournamentById(idTournamentIntegerInteger)
                .orElseThrow(() -> new TournamentNotFoundException(idTournamentIntegerInteger));
        List<Match> matches = matchService.getListOfMatchesByTournament(tournament)
                .orElse(Collections.emptyList());
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(matches));
    }

    @CrossOrigin
    @PatchMapping("/{idMatch}/update-result")
    @ApiOperation("Update the result of a match")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> updateMatchResults(
            @PathVariable String idMatch,
            @RequestParam(name = "scoreTeam1") String scoreTeam1,
            @RequestParam(name = "scoreTeam2") String scoreTeam2
    ) throws Exception {
        Integer idMatchInteger = Integer.valueOf(idMatch),
                scoreTeam1Integer = Integer.valueOf(scoreTeam1),
                scoreTeam2Integer = Integer.valueOf(scoreTeam2);
        Match match = matchService.getMatchById(idMatchInteger)
                .orElseThrow(Exception::new); // TODO: 12/1/2020 create a Exceptions class Match
        Match updateMatch = matchService.updateMatchResult(match, scoreTeam1Integer, scoreTeam2Integer);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(updateMatch));
    }

    @CrossOrigin
    @GetMapping("/all")
    @ApiOperation("Get all the matches currently available")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> getResultsMatch() {
        List<Match> matches = matchService.getAllMatchesByIsFinished(0)
                .orElseGet(Collections::emptyList);
        List<MatchExpectResponse> objects = matchService.getListMatchWithOutcomeForecast(matches)
                .orElseGet(Collections::emptyList);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(objects));
    }
}
