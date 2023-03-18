package pl.quiz.domain.constraint;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.QuestionAnswerDto;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.service.QuestionAnswerService;
import pl.quiz.domain.service.QuestionService;
import pl.quiz.domain.service.RoomService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.Objects;

//TODO
//merge with IsRoomAlreadyOpen

@AllArgsConstructor
public class IsRoomOpenValidator implements ConstraintValidator<IsRoomOpen, QuestionAnswerDto> {

    private final RoomService roomService;

    @Override
    public boolean isValid(QuestionAnswerDto questionAnswerDto, ConstraintValidatorContext ctx) {

        try {
            RoomDto roomDto = roomService.getRoomByQuestionId(questionAnswerDto.getQuestionId());
            return LocalDateTime.now().isAfter(roomDto.getStartDate()) &&
                    LocalDateTime.now().isBefore(roomDto.getEndDate());

        } catch (IllegalArgumentException ex) {
            return true;
        }

    }
}
