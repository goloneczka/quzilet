package pl.quiz.domain.validator;

import lombok.RequiredArgsConstructor;

import javax.validation.Validator;

@RequiredArgsConstructor
public class ValidatorUtil {

    private final Validator validator;
    private final ValidationResult result;

    private void validate(Object objectToValidate) {
        result.setValidateResult(validator.validate(objectToValidate));
    }

    public void checkValidation(Object objectToValidate){
        validate(objectToValidate);
        if (!result.isSuccess()){
            throw new ValidatorException("Validation error !", result);
        }
    }

}
