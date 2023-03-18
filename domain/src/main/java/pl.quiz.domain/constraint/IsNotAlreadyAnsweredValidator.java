package pl.quiz.domain.constraint;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.QuestionAnswerDto;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.service.QuestionAnswerService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
public class IsNotAlreadyAnsweredValidator implements ConstraintValidator<IsNotAlreadyAnswered, QuestionAnswerDto> {

    private final QuestionAnswerService questionAnswerService;

    @Override
    public boolean isValid(QuestionAnswerDto questionAnswerDto, ConstraintValidatorContext ctx) {

        if(Objects.isNull(questionAnswerDto.getQuestionId()) || Objects.isNull(questionAnswerDto.getUserAnswer())){
            return true;
        }
        return !questionAnswerService.isUserAnswerAlreadyExist(questionAnswerDto);
    }
}
