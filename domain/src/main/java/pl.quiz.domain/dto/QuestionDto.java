package pl.quiz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.quiz.domain.ErrorConstraint;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;
    private String uuid;

    @NotBlank
    private String name;
    private String ask;
    private String ask2;
    private String ask3;
    private String ask4;

    @Min(value = 1, message = ErrorConstraint.CORRECT_QUESTION_VALUE)
    @Max(value = 4, message = ErrorConstraint.CORRECT_QUESTION_VALUE)
    private Integer correctAnswerNumber;

}
