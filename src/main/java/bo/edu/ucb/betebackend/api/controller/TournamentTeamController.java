package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.TeamNotFoundException;
import bo.edu.ucb.betebackend.api.exception.TournamentNotFoundException;
import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.TournamentTeam;
import bo.edu.ucb.betebackend.domain.dto.response.FormatResponse;
import bo.edu.ucb.betebackend.domain.dto.model.TournamentTeamResponseModel;
import bo.edu.ucb.betebackend.domain.service.TeamService;
import bo.edu.ucb.betebackend.domain.service.TournamentService;
import bo.edu.ucb.betebackend.domain.service.TournamentTeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @ApiOperation("Enroll a team in a Tournament")
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

    @CrossOrigin
    @GetMapping("/{idTournament}/pending")
    @ApiOperation("Get teams that not have accepted in a tournament yet")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> getTournamentsPending(
            @PathVariable String idTournament
    ) throws NumberFormatException {
        Integer idTournamentInteger = Integer.parseInt(idTournament);
        Tournament tournament = tournamentService.getTournamentById(idTournamentInteger)
                .orElseThrow(() -> new TournamentNotFoundException(idTournamentInteger));
        List<TournamentTeamResponseModel> tournamentTeamResponseModels =
                tournamentTeamService.getTournamentPendingList(tournament)
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(TournamentTeamResponseModel::new)
                        .collect(Collectors.toList());
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(tournamentTeamResponseModels));
    }

    @CrossOrigin
    @PatchMapping("/{idTournamentTeam}/accept")
    @ApiOperation("Action that accept a team in a tournament")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> patchPhaseTournamentTeam(

            @PathVariable String idTournamentTeam
    ) throws Exception {
        Integer idTournamentInteger = Integer.parseInt(idTournamentTeam);
        TournamentTeam tournamentTeam = tournamentTeamService.getTournamentTeamById(idTournamentInteger)
                .orElseThrow(Exception::new); // TODO: 11/17/2020 Create a new Exception
        tournamentTeamService.updatePhaseTournamentTeam(tournamentTeam, 1);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>("", HttpStatus.OK.toString()));
    }

    @CrossOrigin
    @PatchMapping("/reject/{idTournamentTeam}")
    @ApiOperation("Action that reject a team in a tournament")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> patchPhasesTournamentTeam(
            @PathVariable String idTournamentTeam
    ) throws Exception {
        Integer idTournamentInteger = Integer.parseInt(idTournamentTeam);
        TournamentTeam tournamentTeam = tournamentTeamService.getTournamentTeamById(idTournamentInteger)
                .orElseThrow(Exception::new); // TODO: 11/17/2020 Create a new Exception
        tournamentTeamService.updatePhaseTournamentTeam(tournamentTeam, -1);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>("", HttpStatus.OK.toString()));
    }

}
