package bo.edu.ucb.betebackend.api.exception;

public class OrganizerNotFoundException extends RuntimeException {
    public OrganizerNotFoundException(Integer id) {
        super("Organizer not found: " + id);
    }
}
