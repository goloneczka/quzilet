package pl.quiz.domain.constraint;

import pl.quiz.domain.ErrorConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsProperDateRangeValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsProperDateRange {

    String message() default ErrorConstraint.ROOM_DATES_ARE_WRONG;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
