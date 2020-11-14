package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.TournamentTeam;
import bo.edu.ucb.betebackend.domain.repository.ITournamentTeamRepository;
import bo.edu.ucb.betebackend.persistence.dao.TournamentTeamRepository;
import bo.edu.ucb.betebackend.persistence.entity.TournamentTeamEntity;
import bo.edu.ucb.betebackend.persistence.mapper.TournamentTeamMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TournamentTeamDao implements ITournamentTeamRepository {
    private final TournamentTeamMapper tournamentTeamMapper;
    private final TournamentTeamRepository tournamentTeamRepository;

    public TournamentTeamDao(TournamentTeamMapper tournamentTeamMapper, TournamentTeamRepository tournamentTeamRepository) {
        this.tournamentTeamMapper = tournamentTeamMapper;
        this.tournamentTeamRepository = tournamentTeamRepository;
    }

    @Override
    public Optional<TournamentTeam> getTournamentTeamById(Integer id) {
        return tournamentTeamRepository.findById(id)
                .map(tournamentTeamMapper::toTournamentTeam);
    }

    @Override
    public Optional<List<TournamentTeam>> getListOfTournamentTeams() {
        List<TournamentTeamEntity> tournamentTeamEntities =  tournamentTeamRepository.findAll();
        return Optional.ofNullable(tournamentTeamMapper.toListTournamentTeam(tournamentTeamEntities));
    }

    @Override
    public TournamentTeam saveTournamentTeam(TournamentTeam tournamentTeam) {
        return tournamentTeamMapper.toTournamentTeam(tournamentTeamRepository.save(tournamentTeamMapper.toTournamentEntity(tournamentTeam)));
    }

    @Override
    public void deleteTournamentTeam(Integer tournamentTeamId) {
        tournamentTeamRepository.deleteById(tournamentTeamId);
    }
}
