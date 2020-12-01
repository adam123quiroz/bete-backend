package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.TournamentNotFoundException;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import bo.edu.ucb.betebackend.domain.service.MatchService;
import bo.edu.ucb.betebackend.domain.service.TeamService;
import bo.edu.ucb.betebackend.domain.service.TournamentService;
import bo.edu.ucb.betebackend.domain.utils.LeagueUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("save a team with users")
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
    @ApiOperation("save a team with users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> getMatch(@PathVariable String idTournament)
            throws NumberFormatException {
        Integer idTournamentIntegerInteger = Integer.valueOf(idTournament);
        Tournament tournament = tournamentService.getTournamentById(idTournamentIntegerInteger)
                .orElseThrow(() -> new TournamentNotFoundException(idTournamentIntegerInteger));
        return null;
    }
}
