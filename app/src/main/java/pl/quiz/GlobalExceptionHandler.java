package pl.quiz;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.quiz.domain.ValidationMapper;
import pl.quiz.domain.dto.FailedValidationDto;
import pl.quiz.domain.validator.ValidatorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<FailedValidationDto> handleValidatorException(ValidatorException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(ValidationMapper.toDto(exception));
    }

}
