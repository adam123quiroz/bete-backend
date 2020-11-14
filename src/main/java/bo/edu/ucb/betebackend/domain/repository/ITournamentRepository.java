package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.domain.Tournament;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITournamentRepository {
    Optional<List<Tournament>> getListTournamentByOrganizer(Organizer organizer);
    Optional<List<Tournament>> getListTournamentByStartDateAfter(Date date);

    Optional<Tournament> getTournamentById(Integer id);
    Optional<List<Tournament>> getListTournamentById();
    Tournament saveTournament(Tournament tournament);
    void removeTournament(Tournament tournament);
}
