package pl.quiz.domain.constraint;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.OpenRoom;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.service.RoomService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

@AllArgsConstructor
public class IsRoomAlreadyOpenValidator implements ConstraintValidator<IsRoomAlreadyOpen, OpenRoom> {

    private final RoomService roomService;

    @Override
    public boolean isValid(OpenRoom openRoom, ConstraintValidatorContext ctx) {
        RoomDto roomDto;
        try {
            roomDto = roomService.getRoom(openRoom.getRoomId());
        } catch (IllegalArgumentException ex) {
            return true;
        }
        return roomDto.getStartDate().isBefore(openRoom.getCurrentTime());
    }
}
