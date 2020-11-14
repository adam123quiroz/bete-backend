package bo.edu.ucb.betebackend.api.exception.advice;

import bo.edu.ucb.betebackend.api.exception.GameNotFoundException;
import bo.edu.ucb.betebackend.api.exception.RegionNotFoundException;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GameControllerAdvice {
    @ResponseBody
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    FormatResponse<?> gameNotFoundHandler(GameNotFoundException ex) {
        return new FormatResponse<>(ex.getMessage(), null);
    }
}
