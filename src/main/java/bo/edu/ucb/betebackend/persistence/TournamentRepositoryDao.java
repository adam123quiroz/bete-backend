package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.repository.ITournamentRepository;
import bo.edu.ucb.betebackend.persistence.dao.TournamentRepository;
import bo.edu.ucb.betebackend.persistence.entity.OrganizerEntity;
import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import bo.edu.ucb.betebackend.persistence.mapper.OrganizerMapper;
import bo.edu.ucb.betebackend.persistence.mapper.TournamentMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TournamentRepositoryDao implements ITournamentRepository {
    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;
    private final OrganizerMapper organizerMapper;

    public TournamentRepositoryDao(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper, OrganizerMapper organizerMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
        this.organizerMapper = organizerMapper;
    }

    @Override
    public Optional<List<Tournament>> getListTournamentByOrganizer(Organizer organizer) {
        OrganizerEntity organizerEntity = organizerMapper.toOrganizerEntity(organizer);
        List<Tournament> tournaments = tournamentMapper.toListTournament(tournamentRepository.findAllByOrganizer(organizerEntity));
        return Optional.ofNullable(tournaments);
    }

    @Override
    public Optional<List<Tournament>> getListTournamentByStartDateAfter(Date date) {
        List<TournamentEntity> tournamentEntities = tournamentRepository.findAllByStartDateAfter(date);
        return Optional.ofNullable(tournamentMapper.toListTournament(tournamentEntities));
    }

    @Override
    public Optional<Tournament> getTournamentById(Integer id) {
        return tournamentRepository.findById(id).map(tournamentMapper::toTournament);
    }

    @Override
    public Optional<List<Tournament>> getListTournamentById() {
        List<Tournament> tournamentList = tournamentMapper.toListTournament(tournamentRepository.findAll());
        return Optional.ofNullable(tournamentList);
    }

    @Override
    public Tournament saveTournament(Tournament tournament) {
        return tournamentMapper.toTournament(tournamentRepository.save(tournamentMapper.toTournamentEntity(tournament)));
    }

    @Override
    public void removeTournament(Tournament tournament) {
        tournamentRepository.delete(tournamentMapper.toTournamentEntity(tournament));
    }
}
