package pl.quiz.infrastructure.question;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.QuestionToAskDto;
import pl.quiz.domain.port.QuestionPersistencePort;

import java.util.Objects;

@AllArgsConstructor
public class QuestionPersistenceAdapter implements QuestionPersistencePort {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;

    @Override
    public QuestionToAskDto getNextQuestionByCurrentId(Long id) {
        QuestionEntity entity = repository.getNextQuestionByCurrentId(id)
                .orElse(null);

        return Objects.isNull(entity) ? null : mapper.questionToDto(entity);
    }

    @Override
    public boolean isQuestionExistById(Long id) {
        return repository.existsById(id);
    }


}
