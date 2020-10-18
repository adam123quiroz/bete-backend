package bo.edu.ucb.betebackend.api.exception;

public class UserPasswordNotEqualsException extends RuntimeException {
    public UserPasswordNotEqualsException(Integer idUser) {
        super("password not equals user: {}" + idUser);
    }
}
