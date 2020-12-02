package bo.edu.ucb.betebackend.api.exception;

public class BetNoCreditException extends RuntimeException {
    public BetNoCreditException() {
        super("No Credit for the Bet");
    }
}
