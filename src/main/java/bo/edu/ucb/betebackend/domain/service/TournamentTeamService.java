package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.TournamentTeam;
import bo.edu.ucb.betebackend.domain.repository.ITournamentTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentTeamService {
    private final ITournamentTeamRepository tournamentTeamRepository;

    public TournamentTeamService(ITournamentTeamRepository tournamentTeamRepository) {
        this.tournamentTeamRepository = tournamentTeamRepository;
    }

    public TournamentTeam saveTournamentTeam(Team team, Tournament tournament) {
        TournamentTeam tournamentTeam = new TournamentTeam();
        tournamentTeam.setTeam(team);
        tournamentTeam.setTournament(tournament);
        tournamentTeam.setPhase(0);
        tournamentTeam.setStatus(1);
        return tournamentTeamRepository.saveTournamentTeam(tournamentTeam);
    }

    public Optional<List<TournamentTeam>> getTournamentPendingList(Tournament tournament) {
        return tournamentTeamRepository
                .getListTournamentTeamsByTournamentAndPhase(tournament, 0);
    }

    public Optional<TournamentTeam> getTournamentTeamById(Integer tournamentId) {
        return tournamentTeamRepository.getTournamentTeamById(tournamentId);
    }

    public void updatePhaseTournamentTeam(TournamentTeam tournamentTeam, int i) {
        tournamentTeam.setPhase(i);
        tournamentTeamRepository.saveTournamentTeam(tournamentTeam);
    }
}
