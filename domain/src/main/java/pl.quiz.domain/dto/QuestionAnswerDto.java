package pl.quiz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.quiz.domain.ErrorConstraint;
import pl.quiz.domain.constraint.IsQuestionExist;
import pl.quiz.domain.constraint.IsTempUserExist;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerDto {


    private Long id;
    @Min(value = 1, message = ErrorConstraint.CORRECT_QUESTION_VALUE)
    @Max(value = 4, message = ErrorConstraint.CORRECT_QUESTION_VALUE)
    private Long userAnswer;
    @IsQuestionExist
    private Long questionId;

}
