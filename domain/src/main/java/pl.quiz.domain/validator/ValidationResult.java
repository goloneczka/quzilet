package pl.quiz.domain.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
public class ValidationResult {
    
    private Set<ConstraintViolation<Object>> violations;

    private boolean isSuccess;

    void setValidateResult(Set<ConstraintViolation<Object>> violations){
        this.violations = violations;
        isSuccess = violations.isEmpty();
    }
    
}
