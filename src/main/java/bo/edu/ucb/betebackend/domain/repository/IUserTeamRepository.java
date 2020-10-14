package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.UserTeam;

import java.util.List;
import java.util.Optional;

public interface IUserTeamRepository {
    Optional<List<UserTeam>> getUserTeams();
    Optional<UserTeam> getUserTeamById(Integer id);
    UserTeam saveUserTeam(UserTeam newUserTeam);
    List<UserTeam> getAllByUser(User user);
    List<UserTeam> getAllByUserAndIsCapitan(User user, Integer isCapitan);
    List<UserTeam> getAllByTeam(Team team);
    void deleteUserTeam(Integer id);
}
