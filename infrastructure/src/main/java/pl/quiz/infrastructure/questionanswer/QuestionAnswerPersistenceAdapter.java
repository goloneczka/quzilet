package pl.quiz.infrastructure.questionanswer;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.QuestionAnswer;
import pl.quiz.domain.dto.QuestionAnswerDto;
import pl.quiz.domain.port.QuestionAnswerPersistencePort;

@AllArgsConstructor
public class QuestionAnswerPersistenceAdapter implements QuestionAnswerPersistencePort {

    private final QuestionAnswerRepository repository;
    private final QuestionAnswerMapper mapper;


    @Override
    public Long create(QuestionAnswer dto) {
        QuestionAnswerEntity entity = mapper.toEntity(dto);
        return repository.save(entity).getId();
    }

    @Override
    public QuestionAnswer get(Long id) {
        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Temp User with given uuid didnt find"))
        );
    }

    @Override
    public boolean isUserAnswerAlreadyExist(QuestionAnswerDto questionAnswerDto) {
        return repository.existsByQuestionIdAndUserAnswer(
                questionAnswerDto.getQuestionId(), questionAnswerDto.getUserAnswer()
        );
    }

}
