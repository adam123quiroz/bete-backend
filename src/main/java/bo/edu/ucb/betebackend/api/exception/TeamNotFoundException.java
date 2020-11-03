package bo.edu.ucb.betebackend.api.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Integer idTeamInteger) {
        super("Could not find team " + idTeamInteger);
    }
}
