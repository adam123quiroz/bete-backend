package bo.edu.ucb.betebackend.api.exception.advice;

import bo.edu.ucb.betebackend.api.exception.BetNoCreditException;
import bo.edu.ucb.betebackend.domain.dto.response.FormatResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BetControllerAdvice {
    @ResponseBody
    @ExceptionHandler(BetNoCreditException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    FormatResponse<?> gameNotFoundHandler(BetNoCreditException ex) {
        return new FormatResponse<>(ex.getMessage(), null);
    }
}
