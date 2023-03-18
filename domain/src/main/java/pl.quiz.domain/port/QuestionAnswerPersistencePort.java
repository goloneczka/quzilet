package pl.quiz.domain.port;

import pl.quiz.domain.dto.QuestionAnswer;
import pl.quiz.domain.dto.QuestionAnswerDto;


public interface QuestionAnswerPersistencePort {

    Long create(QuestionAnswer dto);

    QuestionAnswer get(Long id);

    boolean isUserAnswerAlreadyExist(QuestionAnswerDto questionAnswerDto);
}
