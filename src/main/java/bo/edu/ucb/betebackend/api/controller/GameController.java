package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.domain.dto.response.FormatResponse;
import bo.edu.ucb.betebackend.domain.service.GameService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Validated
@CrossOrigin("http://localhost:3000")
@RequestMapping("/game")
public class GameController {
    final private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @CrossOrigin
    @GetMapping("/all")
    @ApiOperation("get all the games available")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<FormatResponse<List<Game>>> getAllGames() {
        return new ResponseEntity<>(new FormatResponse<>(null,
                gameService
                        .getAllGame()
                        .orElseGet(Collections::emptyList)),
                HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ApiOperation("Get a game by its id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<Game>> getGameById(
            @PathVariable("id") String id) {
        return gameService.getGameById(Integer.valueOf(id))
                .map(game -> new ResponseEntity<>(
                        new FormatResponse<>(null, game),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(new FormatResponse<>(
                        HttpStatus.NOT_FOUND.toString(),
                        null), HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @PostMapping("/save")
    @ApiOperation("Save a new game")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<Game>> saveGame(
            @RequestBody Game game) {
        try {
            return new ResponseEntity<>(new FormatResponse<>(null, gameService.saveGame(game)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new FormatResponse<>(HttpStatus.BAD_REQUEST.toString(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a game by its id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<String>> removeGame(
            @PathVariable("id") Integer id) {
        if (gameService.removeGame(id)) {
            return new ResponseEntity<>(new FormatResponse<>(null, HttpStatus.OK.toString()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FormatResponse<>(HttpStatus.NOT_FOUND.toString(), null), HttpStatus.NOT_FOUND);
        }
    }
}
