package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import bo.edu.ucb.betebackend.domain.service.GameService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    final private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/all")
    @ApiOperation("get all the games available")
    @ApiResponse(code = 200, message = "OK")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<FormatResponse<List<Game>>> getAllGames() {
        return new ResponseEntity<>(new FormatResponse<>(null,
                gameService
                        .getAllGame()
                        .orElseGet(Collections::emptyList)),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("authentication for register users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<FormatResponse<Game>> getGameById(@PathVariable("id") Integer id) {
        return gameService.getGameById(id)
                .map(game -> new ResponseEntity<>(
                        new FormatResponse<>(null, game),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(new FormatResponse<>(
                        HttpStatus.NOT_FOUND.toString(),
                        null), HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @ApiOperation("authentication for register users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<FormatResponse<Game>> saveGame(@RequestBody Game game) {
        try {
            return new ResponseEntity<>(new FormatResponse<>(null, gameService.saveGame(game)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new FormatResponse<>(HttpStatus.BAD_REQUEST.toString(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("authentication for register users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<FormatResponse<String>> removeGame(@PathVariable("id") Integer id) {
        if (gameService.removeGame(id)) {
            return new ResponseEntity<>(new FormatResponse<>(null, HttpStatus.OK.toString()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FormatResponse<>(HttpStatus.NOT_FOUND.toString(), null), HttpStatus.NOT_FOUND);
        }
    }
}
