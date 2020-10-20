package bo.edu.ucb.betebackend.api.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(Integer role) {
        super("can not found the role: " + role);
    }
}
