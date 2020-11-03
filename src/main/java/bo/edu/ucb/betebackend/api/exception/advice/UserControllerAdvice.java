package bo.edu.ucb.betebackend.api.exception.advice;

import bo.edu.ucb.betebackend.api.exception.RoleNotFoundException;
import bo.edu.ucb.betebackend.api.exception.UserNotFoundException;
import bo.edu.ucb.betebackend.api.exception.UserPasswordNotEqualsException;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserControllerAdvice {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    FormatResponse<String> userNotFoundHandler(UserNotFoundException ex) {
        return new FormatResponse<>(ex.getMessage(), null);
    }

    @ResponseBody
    @ExceptionHandler(UserPasswordNotEqualsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    FormatResponse<String> userPasswordNotEqualsHandler(UserPasswordNotEqualsException ex) {
        return new FormatResponse<>(ex.getMessage(), null);
    }

    @ResponseBody
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    FormatResponse<String> userRoleNotFoundHandler(RoleNotFoundException ex) {
        return new FormatResponse<>(ex.getMessage(), null);
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    FormatResponse<?> userBadCredentialsException(BadCredentialsException e) {
        return new FormatResponse<>(e.getMessage(), null);
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    FormatResponse<?> userUsernameNotFoundHandler(UsernameNotFoundException e) {
        return new FormatResponse<>(e.getMessage(), null);
    }
}
