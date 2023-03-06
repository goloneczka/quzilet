package pl.quiz.domain.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TempUserFinishDataVO {

    private String questionName;
    private Integer correctAnswerNumber;
    private Integer userAnswer;

}
