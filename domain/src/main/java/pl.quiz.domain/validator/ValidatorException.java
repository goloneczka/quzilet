package pl.quiz.domain.validator;

import lombok.Getter;

@Getter
public class ValidatorException extends RuntimeException {

    private final ValidationResult validationResult;

    ValidatorException(String message, ValidationResult result) {
        super(message);
        validationResult = result;
    }
}
