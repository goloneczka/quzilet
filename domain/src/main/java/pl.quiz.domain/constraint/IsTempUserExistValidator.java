package pl.quiz.domain.constraint;

import lombok.AllArgsConstructor;
import pl.quiz.domain.service.RoomService;
import pl.quiz.domain.service.TemporaryUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class IsTempUserExistValidator implements ConstraintValidator<IsTempUserExist, String> {

    private final TemporaryUserService temporaryUserService;

    @Override
    public boolean isValid(String userUuid, ConstraintValidatorContext ctx) {
        return temporaryUserService.isTempUserExist(userUuid);
    }
}
