package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.domain.service.GameService;
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
    public ResponseEntity<List<Game>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGame()
                .orElseGet(Collections::emptyList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") Integer id) {
        return gameService.getGameById(id)
                .map(game -> new ResponseEntity<>(game, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        return new ResponseEntity<>(gameService.saveGame(game), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeGame(@PathVariable("id") Integer id) {
        if (gameService.removeGame(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
