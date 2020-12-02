package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.TeamNotFoundException;
import bo.edu.ucb.betebackend.api.exception.UserNotFoundException;
import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.UserTeam;
import bo.edu.ucb.betebackend.domain.dto.*;
import bo.edu.ucb.betebackend.domain.dto.model.TeamModel;
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
import java.util.stream.Collectors;

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
        Team newTeam = new Team();
        newTeam.setTeamName(request.getTeamName());
        newTeam.setOrganization(request.getOrganization());
        newTeam.setStatus(1);
        User userCreator = userDetailsService.getUserById(request.getCreator())
                .orElseThrow(() -> new UserNotFoundException(request.getCreator()));
        List<Integer> idsList = request.getIdsUserList();
        Team teamSaved = teamService.saveTeam(newTeam);

        userTeamService.addUsersToNewTeam(userCreator, idsList, teamSaved);

        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(null, HttpStatus.OK.name()));

    }

    @CrossOrigin
    @GetMapping("/{idUser}/not-answer")
    @ApiOperation("Get invitations to teams that have not been answered yet")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<List<ResponseUserNotAnswer>>> getRequestNotAnswer(
            @PathVariable("idUser") Integer idUser
    ) throws Exception {
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
        @ApiOperation("Update status of team member")
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
    @ApiOperation("Get all the teams where a user is captain")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found user"),
    })
    public ResponseEntity<FormatResponse<List<TeamAndUserByCapitanResponse>>> getTheUsersAndTeamByCapitan(
            @PathVariable Integer idUser
    ) {
        User user = userDetailsService.getUserById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        List<TeamAndUserByCapitanResponse> responses = userTeamService.getAllTeamAndUsersByCapitanUser(user);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(responses));
    }

    @CrossOrigin
    @PostMapping("/change-captain")
    @ApiOperation("Action that changes a captain")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<String>> changeCaptainUserTeam(
            @RequestBody ChangeCaptainRequest request
    ) throws Exception {
        Team team = teamService.getTeamById(request.getIdTeam()).orElseThrow(Exception::new);
        userTeamService.changeCapitan(team, request.getIdUserOldCaptainInteger(), request.getIdUserNewCaptainInteger());
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(HttpStatus.OK.name()));
    }

    @CrossOrigin
    @PatchMapping("/{idTeam}/remove-user-team/{idUser}")
    @ApiOperation("Action for eliminate a member of a team")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> deleteUserTeam(
            @PathVariable String idTeam,
            @PathVariable String idUser
    ) throws Exception {
        Integer idUserInteger = Integer.valueOf(idUser);
        Integer idTeamInteger = Integer.valueOf(idTeam);
        User user = userDetailsService.getUserById(idUserInteger)
                .orElseThrow(() -> new UserNotFoundException(idUserInteger));
        Team team = teamService.getTeamById(idTeamInteger)
                .orElseThrow(() -> new TeamNotFoundException(idTeamInteger));
        userTeamService.deleteUserMemberOfTeam(user, team);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>("", HttpStatus.OK));
    }

    @CrossOrigin
    @GetMapping("/team/{idTeam}")
    @ApiOperation("Get info of a team")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> getTeamById(@PathVariable String idTeam) throws NumberFormatException {
        Integer idTeamInteger = Integer.valueOf(idTeam);
        Team team = teamService.getTeamById(idTeamInteger).orElseThrow(() -> new TeamNotFoundException(idTeamInteger));
        TeamWithUsersResponse teamWithUsers = userTeamService.getTeamWithUsersById(team);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(teamWithUsers));
    }

    @CrossOrigin
    @GetMapping("/{idUser}/all")
    @ApiOperation("Get all the teams where a user is participating, but isnt a captain")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<?>> getListTeamByUser(
            @PathVariable String idUser) throws NumberFormatException {
        Integer idTeamInteger = Integer.parseInt(idUser);
        User user = userDetailsService.getUserById(idTeamInteger)
                .orElseThrow(() -> new UserNotFoundException(idTeamInteger));
        List<TeamModel> collect = userTeamService.getAllTeamByUserAndUserIsNotCapitan(user)
                .stream()
                .map(TeamModel::apply)
                .collect(Collectors.toList());
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(collect));

    }
}
