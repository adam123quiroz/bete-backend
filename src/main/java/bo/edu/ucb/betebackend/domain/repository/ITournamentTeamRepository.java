package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.TournamentTeam;

import java.util.List;
import java.util.Optional;

public interface ITournamentTeamRepository {
    Optional<TournamentTeam> getTournamentTeamById(Integer id);
    Optional<List<TournamentTeam>> getListOfTournamentTeams();
    TournamentTeam saveTournamentTeam(TournamentTeam tournamentTeam);
    void deleteTournamentTeam(Integer tournamentTeamId);
}
