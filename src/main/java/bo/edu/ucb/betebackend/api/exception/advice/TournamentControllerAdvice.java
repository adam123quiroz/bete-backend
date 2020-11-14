package bo.edu.ucb.betebackend.api.exception.advice;

import bo.edu.ucb.betebackend.api.exception.TournamentNotFoundException;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TournamentControllerAdvice {
    @ResponseBody
    @ExceptionHandler(TournamentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public FormatResponse<?> organizerNotFoundAdvice(TournamentNotFoundException e) {
        return new FormatResponse<>(e.getMessage(), null);
    }
}
