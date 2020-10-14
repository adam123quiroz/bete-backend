package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.repository.ITeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TeamService {
    final private ITeamRepository teamRepository;

    public TeamService(ITeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team saveTeam(Team teamEntity) {
        return teamRepository.saveTeam(teamEntity);
    }

    public Optional<Team> getTeamById(Integer id) {
        return teamRepository.getTeamById(id);
    }

}
