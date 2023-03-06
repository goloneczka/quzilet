package pl.quiz.domain.service;

import lombok.RequiredArgsConstructor;
import pl.quiz.domain.dto.QuestionToAskDto;
import pl.quiz.domain.port.QuestionPersistencePort;
import pl.quiz.domain.validator.ValidatorUtil;

@RequiredArgsConstructor
public class QuestionService {

    private final QuestionPersistencePort questionPersistencePort;
    private final ValidatorUtil validator;

    public QuestionToAskDto getNextQuestion(Long id){
        return questionPersistencePort.getNextQuestionByCurrentId(id);
    }

    public boolean isQuestionExist(Long id){
        return questionPersistencePort.isQuestionExistById(id);
    }
}
