package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.GameNotFoundException;
import bo.edu.ucb.betebackend.api.exception.OrganizerNotFoundException;
import bo.edu.ucb.betebackend.api.exception.TournamentNotFoundException;
import bo.edu.ucb.betebackend.api.exception.UserNotFoundException;
import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.dto.response.FormatResponse;
import bo.edu.ucb.betebackend.domain.dto.request.TournamentPostRequest;
import bo.edu.ucb.betebackend.domain.dto.request.TournamentRequestUpdate;
import bo.edu.ucb.betebackend.domain.service.GameService;
import bo.edu.ucb.betebackend.domain.service.OrganizerService;
import bo.edu.ucb.betebackend.domain.service.TournamentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    private final GameService gameService;
    private final OrganizerService organizerService;

    public TournamentController(TournamentService tournamentService, GameService gameService, OrganizerService organizerService) {
        this.tournamentService = tournamentService;
        this.gameService = gameService;
        this.organizerService = organizerService;
    }

    @CrossOrigin
    @PostMapping("/create")
    @ApiOperation("Create a Tournament")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> saveTournament(
            @NotNull @RequestBody TournamentPostRequest request
    ) throws ParseException {
        Organizer organizer = organizerService.getOrganizerByUser(request.getIdUser())
                .orElseThrow(() -> new OrganizerNotFoundException(request.getIdUser()));
        Game game = gameService.getGameById(request.getIdGame())
                .orElseThrow(() -> new GameNotFoundException(request.getIdGame()));
        Tournament tournament = new Tournament(request);
        tournament.setGame(game);
        tournament.setOrganizer(organizer);
        Tournament tournamentSaved = tournamentService.saveTournament(tournament);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new FormatResponse<>(tournamentSaved));
    }

    @CrossOrigin
    @GetMapping("/all/user/{idUser}")
    @ApiOperation("Get all the tournaments that a user with idUser creates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> getListTournamentByUser(
            @PathVariable String idUser
    ) throws NumberFormatException, UserNotFoundException {
        Integer idUserInteger = Integer.valueOf(idUser);
        Organizer organizer = organizerService.getOrganizerByUser(idUserInteger)
                .orElseThrow(() -> new OrganizerNotFoundException(idUserInteger));
        List<Tournament> tournaments = tournamentService.getTournamentsByOrganizer(organizer)
                .orElseGet(Collections::emptyList);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(tournaments));
    }

    @CrossOrigin
    @PatchMapping("/{idTournament}/update")
    @ApiOperation("Update info for a tournament")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> updateTournament(
            @PathVariable String idTournament,
            @RequestBody TournamentRequestUpdate request
    ) throws GameNotFoundException, NumberFormatException, UserNotFoundException, OrganizerNotFoundException {
        Integer idTournamentInteger = Integer.parseInt(idTournament);
        Tournament tournament = tournamentService.getTournamentById(idTournamentInteger)
                .orElseThrow(() -> new TournamentNotFoundException(idTournamentInteger));
        Tournament tournamentUpdated = tournamentService.updateTournament(request, tournament);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(tournamentUpdated));
    }

    @CrossOrigin
    @GetMapping("/coming")
    @ApiOperation("Get all the tournament that will be come")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> getTournamentsAreComing() {
        List<Tournament> tournamentsAreComing = tournamentService.getTournamentsAreComing()
                .orElseGet(Collections::emptyList);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(tournamentsAreComing));
    }
}
