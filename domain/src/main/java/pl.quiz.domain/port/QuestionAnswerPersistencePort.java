package pl.quiz.domain.port;

import pl.quiz.domain.dto.QuestionAnswer;

public interface QuestionAnswerPersistencePort {

    Long create(QuestionAnswer dto);

    QuestionAnswer get(Long id);
}
