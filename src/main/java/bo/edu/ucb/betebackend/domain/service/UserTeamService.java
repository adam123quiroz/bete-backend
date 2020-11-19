package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.UserTeam;
import bo.edu.ucb.betebackend.domain.dto.TeamAndUserByCapitanResponse;
import bo.edu.ucb.betebackend.domain.dto.TeamWithUsersResponse;
import bo.edu.ucb.betebackend.domain.dto.UserCapitanResponse;
import bo.edu.ucb.betebackend.domain.dto.model.UserResponse;
import bo.edu.ucb.betebackend.domain.repository.IUserRepository;
import bo.edu.ucb.betebackend.domain.repository.IUserTeamRepository;
import bo.edu.ucb.betebackend.domain.typeof.TypeOfIsCapitanUserTeam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserTeamService {
    final static Logger logger = LoggerFactory.getLogger(UserTeamService.class);

    final private IUserTeamRepository userTeamRepository;
    final private IUserRepository userRepository;

    public UserTeamService(IUserTeamRepository userTeamRepository, IUserRepository userRepository) {
        this.userTeamRepository = userTeamRepository;
        this.userRepository = userRepository;
    }

    private static Team apply(UserTeam userTeam) {
        return new Team(userTeam.getTeam().getIdTeam(),
                userTeam.getTeam().getTeamName(),
                userTeam.getTeam().getOrganization(),
                userTeam.getTeam().getStatus());
    }

    public UserTeam saveUserTeam(UserTeam newUserTeam) {
        return userTeamRepository.saveUserTeam(newUserTeam);
    }

    public List<UserTeam> getAllTeamUserByUserAndNotAnswer(User user) {
        return userTeamRepository.getAllByUser(user)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(userTeam -> userTeam.getIsCaptain() == TypeOfIsCapitanUserTeam.NotAnswer.getStatus())
                .collect(Collectors.toList());
    }

    public Optional<UserTeam> getUserTeamById(Integer idUserTeam) {
        return userTeamRepository.getUserTeamById(idUserTeam);
    }


    public List<TeamAndUserByCapitanResponse> getAllTeamAndUsersByCapitanUser(User user) {
        List<TeamAndUserByCapitanResponse> responses = new ArrayList<>();
        List<Team> teamList = new ArrayList<>();
        List<UserTeam> userTeamsByUserList = userTeamRepository.getAllByUserAndIsCapitan(
                user,
                TypeOfIsCapitanUserTeam.TheCapitan.getStatus()
        ).orElseGet(Collections::emptyList);
        userTeamsByUserList
                .stream()
                .map(UserTeam::getTeam)
                .forEach(teamList::add);

        teamList
                .forEach(team -> responses.add(getTeamAndUserByCapitanResponse(team)));
        return responses;
    }

    private TeamAndUserByCapitanResponse getTeamAndUserByCapitanResponse(Team team) {
        TeamAndUserByCapitanResponse capitanResponse = new TeamAndUserByCapitanResponse();
        List<UserCapitanResponse> users = new ArrayList<>();
        initCapitalResponse(team, capitanResponse);
        getAllUserTeamByTeam(team)
                .forEach(userTeam -> users.add(new UserCapitanResponse(
                        userTeam.getUser().getIdUser(),
                        userTeam.getUser().getUsername(),
                        userTeam.getIsCaptain()
                )));
        capitanResponse.setCapitanResponseList(users);
        return capitanResponse;
    }

    public void changeCapitan(Team team, Integer idOldCapitan, Integer idNewCapitan) {
        getAllUserTeamByTeam(team)
                .forEach(userTeam -> isCapitanAndIsTheUser(
                        idOldCapitan,
                        userTeam,
                        userTeam.getIsCaptain() == TypeOfIsCapitanUserTeam.TheCapitan.getStatus(),
                        TypeOfIsCapitanUserTeam.NotAnswer));

        getAllUserTeamByTeam(team)
                .forEach(userTeam -> isCapitanAndIsTheUser(
                        idNewCapitan,
                        userTeam,
                        userTeam.getIsCaptain() != TypeOfIsCapitanUserTeam.TheCapitan.getStatus(),
                        TypeOfIsCapitanUserTeam.TheCapitan));
    }

    private void isCapitanAndIsTheUser(Integer idNewCapitan, UserTeam userTeam, boolean b, TypeOfIsCapitanUserTeam theCapitan) {
        if (userTeam.getUser().getIdUser().equals(idNewCapitan) &&
                b) {
            logger.info("Entre");
            userTeam.setIsCaptain(theCapitan.getStatus());
            userTeamRepository.saveUserTeam(userTeam);
        } else {
            logger.info("rrrrr");
            //TODO
        }
    }

    public void deleteUserMemberOfTeam(User user, Team team) throws Exception {
        UserTeam userTeam = userTeamRepository.getByTeamAndUser(team, user)
                .orElseThrow(Exception::new); // TODO: 11/17/2020 Create a New Exception
        userTeam.setIsCaptain(-2);
        userTeam.setTeam(team);
        userTeam.setUser(user);
        userTeamRepository.saveUserTeam(userTeam);
    }

    private void initCapitalResponse(Team team, TeamAndUserByCapitanResponse capitanResponse) {
        capitanResponse.setIdTeam(team.getIdTeam());
        capitanResponse.setNameTeam(team.getTeamName());
        capitanResponse.setOrganization(team.getOrganization());
    }

    private void saveUserTeamAndCapitan(Team teamSaved, User user) {
        UserTeam newUserTeam = new UserTeam();
        newUserTeam.setTeam(teamSaved);
        newUserTeam.setStatus(1);
        newUserTeam.setIsCaptain(TypeOfIsCapitanUserTeam.NotAnswer.getStatus());
        newUserTeam.setUser(user);
        userTeamRepository.saveUserTeam(newUserTeam);
    }

    public List<UserTeam> getAllUserTeamByTeam(Team team) {
        return userTeamRepository.getAllByTeam(team)
                .orElseGet(Collections::emptyList);
    }

    public void addUsersToNewTeam(User userCreator, List<Integer> idsList, Team teamSaved) {

        UserTeam userTeamCaptain = new UserTeam();
        userTeamCaptain.setTeam(teamSaved);
        userTeamCaptain.setUser(userCreator);
        userTeamCaptain.setIsCaptain(TypeOfIsCapitanUserTeam.TheCapitan.getStatus());
        userTeamCaptain.setStatus(1);
        userTeamRepository.saveUserTeam(userTeamCaptain);

        idsList
                .stream()
                .map(userRepository::getUserById)
                .map(Optional::get)
                .forEach(user -> saveUserTeamAndCapitan(teamSaved, user));
    }

    public TeamWithUsersResponse getTeamWithUsersById(Team team) {
        TeamWithUsersResponse response = new TeamWithUsersResponse(team);
        List<UserResponse> userResponses = new ArrayList<>();
        userTeamRepository.getAllByTeam(team)
                .ifPresent(userTeams -> userTeams
                        .forEach(userTeam -> userResponses.add(new UserResponse(
                                userTeam.getUser(),
                                userTeam.getIsCaptain()))));
        response.setUserResponseList(userResponses);
        return response;
    }

    public List<Team> getAllTeamByUser(User user) {
        return userTeamRepository.getAllByUser(user)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(UserTeamService::apply)
                .collect(Collectors.toList());
    }

    public List<UserTeam> getAllTeamByUserAndUserIsNotCapitan(User user) {
        return userTeamRepository.getAllByUser(user)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(userTeam -> userTeam.getIsCaptain() == 0 || userTeam.getIsCaptain() == 1)
                .collect(Collectors.toList());
    }
}
