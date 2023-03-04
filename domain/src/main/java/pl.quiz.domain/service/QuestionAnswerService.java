package pl.quiz.domain.service;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.QuestionAnswer;
import pl.quiz.domain.dto.QuestionAnswerDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.port.QuestionAnswerPersistencePort;
import pl.quiz.domain.validator.ValidatorUtil;

@AllArgsConstructor
public class QuestionAnswerService {

    private final QuestionAnswerPersistencePort questionAnswerPersistencePort;
    private final TemporaryUserService temporaryUserService;
    private final ValidatorUtil validatorUtil;

    public Long createQuestionAnswer(QuestionAnswerDto questionAnswerDto){
        validatorUtil.checkValidation(questionAnswerDto);
        TemporaryUserDto userDto = temporaryUserService.getTempUser(questionAnswerDto.getTempUserUuid());
        return questionAnswerPersistencePort.create(buildQuestionAnswer(questionAnswerDto, userDto));
    }

    private QuestionAnswer buildQuestionAnswer(QuestionAnswerDto questionAnswerDto, TemporaryUserDto temporaryUserDto) {
        return QuestionAnswer.builder()
                .questionId(questionAnswerDto.getQuestionId())
                .userAnswer(questionAnswerDto.getUserAnswer())
                .temporaryUserDto(temporaryUserDto)
                .build();
    }
}
