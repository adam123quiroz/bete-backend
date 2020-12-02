package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.UserNotFoundException;
import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.response.FormatResponse;
import bo.edu.ucb.betebackend.domain.dto.request.GamblerRequest;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import bo.edu.ucb.betebackend.domain.service.GamblerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@CrossOrigin("http://localhost:3000")
@RequestMapping("/gambler")
public class GamblerController {
    private final BeteUserDetailsService userDetailsService;
    private final GamblerService gamblerService;

    public GamblerController(BeteUserDetailsService userDetailsService, GamblerService gamblerService) {
        this.userDetailsService = userDetailsService;
        this.gamblerService = gamblerService;
    }

    @CrossOrigin
    @GetMapping("/{idUser}")
    @ApiOperation("save a team with users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> getGamblerByUser(@PathVariable String idUser)
            throws NumberFormatException{
        Integer idUserInt = Integer.parseInt(idUser);
        User user = userDetailsService.getUserById(idUserInt)
                .orElseThrow(() -> new UserNotFoundException(idUserInt));
        Gambler gambler = gamblerService.getGamblerByUser(user)
                .orElseGet(Gambler::new);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(gambler));

    }

    @CrossOrigin
    @PostMapping("/buy")
    @ApiOperation("save a team with users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> buyGambler(@RequestBody GamblerRequest gamblerRequest) {
        User user = userDetailsService.getUserById(gamblerRequest.getIdUser())
                .orElseThrow(() -> new UserNotFoundException(gamblerRequest.getIdUser()));
        Gambler gambler = gamblerService.buyGambler(user, gamblerRequest);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(gambler));
    }
}
