package pl.quiz.domain.constraint;

import lombok.AllArgsConstructor;
import pl.quiz.domain.service.QuestionService;
import pl.quiz.domain.service.RoomService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class IsQuestionExistValidator implements ConstraintValidator<IsQuestionExist, Long> {

    private final QuestionService questionService;

    @Override
    public boolean isValid(Long questionId, ConstraintValidatorContext ctx) {
        return questionService.isQuestionExist(questionId);
    }
}
