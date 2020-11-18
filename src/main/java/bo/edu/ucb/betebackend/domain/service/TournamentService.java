package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.api.exception.GameNotFoundException;
import bo.edu.ucb.betebackend.api.exception.OrganizerNotFoundException;
import bo.edu.ucb.betebackend.api.exception.UserNotFoundException;
import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.TournamentRequestUpdate;
import bo.edu.ucb.betebackend.domain.repository.IGameRepository;
import bo.edu.ucb.betebackend.domain.repository.IOrganizerRepository;
import bo.edu.ucb.betebackend.domain.repository.ITournamentRepository;
import bo.edu.ucb.betebackend.domain.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    private final ITournamentRepository tournamentRepository;
    private final IGameRepository gameRepository;
    private final IOrganizerRepository organizerRepository;
    private final IUserRepository userRepository;

    public TournamentService(ITournamentRepository tournamentRepository, IGameRepository gameRepository, IOrganizerRepository organizerRepository, IUserRepository userRepository) {
        this.tournamentRepository = tournamentRepository;
        this.gameRepository = gameRepository;
        this.organizerRepository = organizerRepository;
        this.userRepository = userRepository;
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
        if (requestUpdate.getName() != null) tournament.setName(requestUpdate.getName());
        if (requestUpdate.getDescription() != null) tournament.setDescription(requestUpdate.getDescription());
        if (requestUpdate.getEndDate() != null) tournament.setEndDate(requestUpdate.getEndDate());
        if (requestUpdate.getStartDate() != null) tournament.setStartDate(requestUpdate.getStartDate());
        if (requestUpdate.getGame() != null) updateGameAtTournament(requestUpdate, tournament);
        if (requestUpdate.getIdUser() != null) updateOrganizerAtTournament(requestUpdate, tournament);
        return tournamentRepository.saveTournament(tournament);
    }

    private void updateGameAtTournament(TournamentRequestUpdate requestUpdate, Tournament tournament) {
        Game game = gameRepository.getGameById(requestUpdate.getGame())
                .orElseThrow(() -> new GameNotFoundException(requestUpdate.getGame()));
        tournament.setGame(game);
    }

    private void updateOrganizerAtTournament(TournamentRequestUpdate requestUpdate, Tournament tournament) {
        User user = userRepository.getUserById(requestUpdate.getIdUser())
                .orElseThrow(() -> new UserNotFoundException(requestUpdate.getIdUser()));
        Organizer organizer = organizerRepository.getOrganizerByUser(user)
                .orElseThrow(() -> new OrganizerNotFoundException(user.getIdUser()));
        tournament.setOrganizer(organizer);
    }
}
