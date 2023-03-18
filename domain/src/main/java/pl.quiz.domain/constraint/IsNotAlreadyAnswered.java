package pl.quiz.domain.constraint;

import pl.quiz.domain.ErrorConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsNotAlreadyAnsweredValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsNotAlreadyAnswered {

    String message() default ErrorConstraint.ANSWER_ALREADY_EXIST;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
