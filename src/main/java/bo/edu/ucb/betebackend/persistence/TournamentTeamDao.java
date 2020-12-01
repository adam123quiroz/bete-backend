package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.TournamentTeam;
import bo.edu.ucb.betebackend.domain.repository.ITournamentTeamRepository;
import bo.edu.ucb.betebackend.persistence.dao.TournamentTeamRepository;
import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import bo.edu.ucb.betebackend.persistence.entity.TournamentTeamEntity;
import bo.edu.ucb.betebackend.persistence.mapper.TournamentMapper;
import bo.edu.ucb.betebackend.persistence.mapper.TournamentTeamMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TournamentTeamDao implements ITournamentTeamRepository {
    private final TournamentTeamRepository tournamentTeamRepository;
    private final TournamentTeamMapper tournamentTeamMapper;
    private final TournamentMapper tournamentMapper;

    public TournamentTeamDao(TournamentTeamMapper tournamentTeamMapper, TournamentTeamRepository tournamentTeamRepository, TournamentMapper tournamentMapper) {
        this.tournamentTeamMapper = tournamentTeamMapper;
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.tournamentMapper = tournamentMapper;
    }

    @Override
    public Optional<List<TournamentTeam>> getListTournamentTeamsByTournamentAndPhase(Tournament tournament, Integer phase) {
        TournamentEntity tournamentEntity = tournamentMapper.toTournamentEntity(tournament);
        List<TournamentTeamEntity> tournamentTeamEntities = tournamentTeamRepository.findAllByTournamentAndPhase(tournamentEntity, phase);
        List<TournamentTeam> tournamentTeams = tournamentTeamMapper.toListTournamentTeam(tournamentTeamEntities);
        return Optional.ofNullable(tournamentTeams);
    }

    @Override
    public Optional<List<TournamentTeam>> getListTournamentTeamsByTournament(Tournament tournament) {
        TournamentEntity tournamentEntity = tournamentMapper.toTournamentEntity(tournament);
        return Optional.ofNullable(tournamentTeamMapper.toListTournamentTeam(tournamentTeamRepository.findAllByTournament(tournamentEntity)));
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
