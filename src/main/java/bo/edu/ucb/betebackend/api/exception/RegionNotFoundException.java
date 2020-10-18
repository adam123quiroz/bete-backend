package bo.edu.ucb.betebackend.api.exception;

public class RegionNotFoundException extends RuntimeException {
    public RegionNotFoundException(Integer id) {
        super("Could not find region " + id
        );
    }
}
