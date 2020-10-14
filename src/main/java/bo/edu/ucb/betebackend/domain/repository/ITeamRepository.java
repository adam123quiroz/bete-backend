package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.Team;

import java.util.List;
import java.util.Optional;

public interface ITeamRepository {
    Optional<List<Team>> getAllTeams();
    Optional<Team> getTeamById(Integer id);
    Team saveTeam(Team team);
    void remove(Integer id);
}
