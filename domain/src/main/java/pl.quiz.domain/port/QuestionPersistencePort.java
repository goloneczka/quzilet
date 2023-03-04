package pl.quiz.domain.port;

import pl.quiz.domain.dto.QuestionToAskDto;

public interface QuestionPersistencePort {

    QuestionToAskDto getNextQuestionByCurrentId(Long id);

    boolean isQuestionExistById(Long id);
}
