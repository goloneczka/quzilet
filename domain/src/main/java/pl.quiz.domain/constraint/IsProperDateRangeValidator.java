package pl.quiz.domain.constraint;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.service.QuestionService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
public class IsProperDateRangeValidator implements ConstraintValidator<IsProperDateRange, RoomDto> {


    @Override
    public boolean isValid(RoomDto roomDto, ConstraintValidatorContext ctx) {
        LocalDateTime startDate = roomDto.getStartDate();
        LocalDateTime endDate = roomDto.getEndDate();

        if(Objects.isNull(startDate) || Objects.isNull(endDate)){
            return true;
        }
        return endDate.isAfter(startDate);
    }
}
