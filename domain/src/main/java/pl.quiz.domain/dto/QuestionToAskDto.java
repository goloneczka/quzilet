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
public class QuestionToAskDto {

    private Long id;
    private String uuid;
    private String name;
    private String ask;
    private String ask2;
    private String ask3;
    private String ask4;


}
