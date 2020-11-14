package bo.edu.ucb.betebackend.api.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Integer idGame) {
        super("Game not found: " + idGame);
    }
}
