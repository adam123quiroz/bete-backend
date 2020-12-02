package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.TeamNotFoundException;
import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.dto.response.FormatResponse;
import bo.edu.ucb.betebackend.domain.dto.model.TeamModel;
import bo.edu.ucb.betebackend.domain.service.TeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/team")
public class TeamController {
    final private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @CrossOrigin
    @PatchMapping("/{idTeam}/updating")
    @ApiOperation("save a team with users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> updateTeam(
            @PathVariable String idTeam,
            @RequestBody TeamModel request
    ) throws NumberFormatException {
        Integer idTeamInteger = Integer.valueOf(idTeam);
        Team team = teamService.getTeamById(idTeamInteger).orElseThrow(() -> new TeamNotFoundException(idTeamInteger));
        Team teamUpdated = teamService.updateTeam(team, request);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(teamUpdated));
    }
}
