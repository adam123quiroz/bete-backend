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
    void deleteUserTeam(Integer id);

    Optional<List<UserTeam>> getAllByUser(User user);
    Optional<List<UserTeam>> getAllByUserAndIsCapitan(User user, Integer isCapitan);
    Optional<List<UserTeam>> getAllByTeam(Team team);
    Optional<UserTeam> getByTeamAndUser(Team team, User user);
}
