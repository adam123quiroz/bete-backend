package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.TeamNotFoundException;
import bo.edu.ucb.betebackend.api.exception.TournamentNotFoundException;
import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import bo.edu.ucb.betebackend.domain.service.TeamService;
import bo.edu.ucb.betebackend.domain.service.TournamentService;
import bo.edu.ucb.betebackend.domain.service.TournamentTeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/tournament-team")
public class TournamentTeamController {
    private final TournamentTeamService tournamentTeamService;
    private final TournamentService tournamentService;
    private final TeamService teamService;

    public TournamentTeamController(TournamentTeamService tournamentTeamService, TournamentService tournamentService, TeamService teamService) {
        this.tournamentTeamService = tournamentTeamService;
        this.tournamentService = tournamentService;
        this.teamService = teamService;
    }

    @CrossOrigin
    @PostMapping("/enroll")
    @ApiOperation("Get user by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> enrollTeamOnTournament(
            @RequestParam(name = "idTeam") String idTeam,
            @RequestParam(name = "idTournament") String idTournament
    ) throws NumberFormatException {
        Integer tournamentId = Integer.valueOf(idTournament);
        Integer teamId = Integer.valueOf(idTeam);
        Team team = teamService.getTeamById(teamId)
                .orElseThrow(() -> new TeamNotFoundException(teamId));
        Tournament tournament = tournamentService.getTournamentById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(tournamentTeamService.saveTournamentTeam(team, tournament)));
    }

}