package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.UserTeam;
import bo.edu.ucb.betebackend.domain.dto.*;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import bo.edu.ucb.betebackend.domain.service.TeamService;
import bo.edu.ucb.betebackend.domain.service.UserTeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/team-user")
public class UserTeamController {
    final static Logger logger = LoggerFactory.getLogger(UserTeamController.class);

    final private TeamService teamService;
    final private BeteUserDetailsService userDetailsService;
    final private UserTeamService userTeamService;

    public UserTeamController(TeamService teamService, BeteUserDetailsService userDetailsService, UserTeamService userTeamService) {
        this.teamService = teamService;
        this.userDetailsService = userDetailsService;
        this.userTeamService = userTeamService;
    }

    @CrossOrigin
    @PostMapping("/creating")
    @ApiOperation("save a team with users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<String>> saveTeamWithUsers(
            @RequestBody TeamUserRequest request) {
        try {
            Team newTeam = request.getTeam();
            List<Integer> idsList = request.getIdsUserList();
            Team teamSaved = teamService.saveTeam(newTeam);

            idsList
                    .stream()
                    .map(userDetailsService::getUserById)
                    .map(Optional::get)
                    .forEach(user -> saveUserTeam(request, teamSaved, user));

            return new ResponseEntity<>(new FormatResponse<>(null, HttpStatus.OK.toString()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FormatResponse<>(HttpStatus.NOT_FOUND.toString(), null), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/{idUser}/not-answer")
    @ApiOperation("save a team with users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<List<ResponseUserNotAnswer>>> getRequestNotAnswer(
            @PathVariable("idUser") Integer idUser
    ) throws Exception {
        logger.info("AQ " + idUser);
        User user = userDetailsService.getUserById(idUser).orElseThrow(Exception::new);
        List<UserTeam> userTeamsByUser = userTeamService.getAllTeamUserByUserAndNotAnswer(user);
        List<ResponseUserNotAnswer> answers = new ArrayList<>(Collections.emptyList());
        userTeamsByUser
                .forEach(userTeam -> answers
                        .add(new ResponseUserNotAnswer(
                                userTeam.getIdUserTeam(),
                                userTeam.getTeam().getTeamName()
                        )));
        return new ResponseEntity<>(new FormatResponse<>(null, answers), HttpStatus.OK);
    }

    @CrossOrigin
    @PatchMapping("/update")
    @ApiOperation("save a team with users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<String>> updateIsCapitan(
            @RequestBody RequestUpdateIsCapitan request
    ) throws Exception {
        UserTeam userTeam = userTeamService.getUserTeamById(request.getIdUserTeam()).orElseThrow(Exception::new);
        if (request.getIsCapitan() == 1 || request.getIsCapitan() == -1) {
            userTeam.setIsCaptain(request.getIsCapitan());
            userTeamService.saveUserTeam(userTeam);
        }
        return new ResponseEntity<>(new FormatResponse<>(null, HttpStatus.OK.toString()), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/capitan/{idUser}")
    @ApiOperation("save a team with users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<List<TeamAndUserByCapitanResponse>>> getTheUsersAndTeamByCapitan(
            @PathVariable Integer idUser
    ) throws Exception {
        User user = userDetailsService.getUserById(idUser).orElseThrow(Exception::new);
        List<TeamAndUserByCapitanResponse> responses = userTeamService.getAllTeamAndUsersByCapitanUser(user);
        return new ResponseEntity<>(new FormatResponse<>(null, responses),
                HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/change-captain")
    @ApiOperation("save a team with users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<String>> changeCaptainUserTeam(
            @RequestBody ChangeCaptainRequest request
    ) throws Exception {
        Team team = teamService.getTeamById(request.getIdTeam()).orElseThrow(Exception::new);
        userTeamService.changeCapitan(team, request.getIdUserOldCaptainInteger(), request.getIdUserNewCaptainInteger());
        return new ResponseEntity<>(new FormatResponse<>(null, HttpStatus.OK.toString()), HttpStatus.OK);
    }


    private void saveUserTeam(TeamUserRequest request, Team teamSaved, User user) {
        UserTeam newUserTeam = new UserTeam();
        newUserTeam.setTeam(teamSaved);
        newUserTeam.setStatus(1);
        newUserTeam.setIsCaptain(request.getIsCapitan());
        newUserTeam.setUser(user);
        userTeamService.saveUserTeam(newUserTeam);
    }
}
