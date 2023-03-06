package pl.quiz.domain.validator;

import lombok.Getter;

@Getter
public class ValidatorException extends RuntimeException {

    private final ValidationResult validationResult;

    public ValidatorException(String message, ValidationResult result) {
        super(message);
        validationResult = result;
    }
}
