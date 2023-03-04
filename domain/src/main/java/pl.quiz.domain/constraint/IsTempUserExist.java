package pl.quiz.domain.constraint;

import pl.quiz.domain.ErrorConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsTempUserExistValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsTempUserExist {

    String message() default ErrorConstraint.TEMP_USER_DOES_NOT_EXIST;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
