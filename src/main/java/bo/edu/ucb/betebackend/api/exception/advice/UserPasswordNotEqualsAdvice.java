package bo.edu.ucb.betebackend.api.exception.advice;

import bo.edu.ucb.betebackend.api.exception.UserNotFoundException;
import bo.edu.ucb.betebackend.api.exception.UserPasswordNotEqualsException;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserPasswordNotEqualsAdvice {
    @ResponseBody
    @ExceptionHandler(UserPasswordNotEqualsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    FormatResponse<String> userPasswordNotEqualsHandler(UserPasswordNotEqualsException ex) {
        return new FormatResponse<>(ex.getMessage(), null);
    }
}
