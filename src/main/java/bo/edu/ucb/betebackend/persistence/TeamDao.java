package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.repository.ITeamRepository;
import bo.edu.ucb.betebackend.persistence.dao.TeamRepository;
import bo.edu.ucb.betebackend.persistence.mapper.TeamMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamDao implements ITeamRepository {
    final private TeamRepository teamRepository;
    final private TeamMapper teamMapper;

    public TeamDao(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Optional<List<Team>> getAllTeams() {
        return Optional.empty();
    }

    @Override
    public Optional<Team> getTeamById(Integer id) {
        return teamRepository.findById(id).map(teamMapper::toTeam);
    }

    @Override
    public Team saveTeam(Team team) {
        return teamMapper.toTeam(teamRepository.save(teamMapper.toTeamEntity(team)));
    }

    @Override
    public void remove(Integer id) {

    }
}
