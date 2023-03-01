package pl.quiz.domain;

import pl.quiz.domain.dto.FailedValidationDto;
import pl.quiz.domain.validator.ValidatorException;

import javax.validation.ConstraintViolation;
import java.util.List;

public class ValidationMapper {

    static public FailedValidationDto toDto(ValidatorException exception) {
        List<String> errors = exception
                .getValidationResult()
                .getViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        return new FailedValidationDto(errors);
    }
}
