package bo.edu.ucb.betebackend.api.exception;

public class TournamentNotFoundException extends RuntimeException {
    public TournamentNotFoundException(Integer tournamentId) {
        super("Not Found Tournament id: " + tournamentId);
    }
}
