package pl.quiz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class QuestionAnswer {

    private Long id;
    private Long userAnswer;
    private Long questionId;
    private TemporaryUserDto temporaryUserDto;

}
