package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.api.exception.GameNotFoundException;
import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.dto.TournamentRequestUpdate;
import bo.edu.ucb.betebackend.domain.repository.IGameRepository;
import bo.edu.ucb.betebackend.domain.repository.ITournamentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    private final ITournamentRepository tournamentRepository;
    private final IGameRepository gameRepository;

    public TournamentService(ITournamentRepository tournamentRepository, IGameRepository gameRepository) {
        this.tournamentRepository = tournamentRepository;
        this.gameRepository = gameRepository;
    }

    public Tournament saveTournament(Tournament tournament) {
        return tournamentRepository.saveTournament(tournament);
    }

    public Optional<List<Tournament>> getTournamentsByOrganizer(Organizer organizer) {
        return tournamentRepository.getListTournamentByOrganizer(organizer);
    }

    public Optional<Tournament> getTournamentById(Integer tournamentId) {
        return tournamentRepository.getTournamentById(tournamentId);
    }

    public Optional<List<Tournament>> getTournamentsAreComing() {
        return tournamentRepository.getListTournamentByStartDateAfter(new Date());
    }

    public Tournament updateTournament(TournamentRequestUpdate requestUpdate, Tournament tournament) {
        if (requestUpdate.getName() != null) {
            tournament.setName(requestUpdate.getName());
        }
        if (requestUpdate.getDescription() != null) {
            tournament.setDescription(requestUpdate.getDescription());
        }
        if (requestUpdate.getEndDate() != null) {
            tournament.setEndDate(requestUpdate.getEndDate());
        }
        if (requestUpdate.getStartDate() != null) {
            tournament.setStartDate(requestUpdate.getStartDate());
        }
        if (requestUpdate.getGame() != null) {
            Game game = gameRepository.getGameById(requestUpdate.getGame())
                    .orElseThrow(() -> new GameNotFoundException(requestUpdate.getGame()));
            tournament.setGame(game);
        }
        return tournamentRepository.saveTournament(tournament);
    }
}
