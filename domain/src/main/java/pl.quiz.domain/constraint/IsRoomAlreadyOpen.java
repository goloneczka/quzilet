package pl.quiz.domain.constraint;

import pl.quiz.domain.ErrorConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsRoomAlreadyOpenValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsRoomAlreadyOpen {

    String message() default ErrorConstraint.ROOM_NOT_OPEN_YET;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
