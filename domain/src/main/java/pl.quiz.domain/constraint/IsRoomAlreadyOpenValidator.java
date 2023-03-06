package pl.quiz.domain.constraint;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.vo.OpenRoomVO;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.service.RoomService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class IsRoomAlreadyOpenValidator implements ConstraintValidator<IsRoomAlreadyOpen, OpenRoomVO> {

    private final RoomService roomService;

    @Override
    public boolean isValid(OpenRoomVO openRoomVO, ConstraintValidatorContext ctx) {
        RoomDto roomDto;
        try {
            roomDto = roomService.getRoom(openRoomVO.getRoomId());
        } catch (IllegalArgumentException ex) {
            return true;
        }
        return roomDto.getStartDate().isBefore(openRoomVO.getCurrentTime());
    }
}
