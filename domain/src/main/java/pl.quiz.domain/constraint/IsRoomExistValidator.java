package pl.quiz.domain.constraint;

import lombok.AllArgsConstructor;
import pl.quiz.domain.service.RoomService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class IsRoomExistValidator implements ConstraintValidator<IsRoomExist, Long> {

    private final RoomService roomService;

    @Override
    public boolean isValid(Long roomId, ConstraintValidatorContext ctx) {
        return roomService.isRoomExistById(roomId);
    }
}
