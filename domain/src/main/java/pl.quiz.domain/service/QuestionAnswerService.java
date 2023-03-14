package pl.quiz.domain.service;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import pl.quiz.domain.dto.QuestionAnswer;
import pl.quiz.domain.dto.QuestionAnswerDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.event.CloseTempUserEvent;
import pl.quiz.domain.dto.vo.TempUserFinishDataVO;
import pl.quiz.domain.dto.vo.TempUserVO;
import pl.quiz.domain.event.EventPublisherWrapper;
import pl.quiz.domain.port.PersistencePortMB;
import pl.quiz.domain.port.QuestionAnswerPersistencePort;
import pl.quiz.domain.validator.ValidatorUtil;

@AllArgsConstructor
public class QuestionAnswerService {

    private final QuestionAnswerPersistencePort questionAnswerPersistencePort;
    private final TemporaryUserService temporaryUserService;
    private final ValidatorUtil validatorUtil;
    private final PersistencePortMB persistencePortMB;
    private final EventPublisherWrapper eventPublisherWrapper;

    public Long createQuestionAnswer(QuestionAnswerDto questionAnswerDto, String userUuid) {
        validatorUtil.checkValidation(questionAnswerDto);
        TemporaryUserDto userDto = temporaryUserService.getTempUser(userUuid);
        return questionAnswerPersistencePort.create(buildQuestionAnswer(questionAnswerDto, userDto));
    }

    private QuestionAnswer buildQuestionAnswer(QuestionAnswerDto questionAnswerDto, TemporaryUserDto temporaryUserDto) {
        return QuestionAnswer.builder()
                .questionId(questionAnswerDto.getQuestionId())
                .userAnswer(questionAnswerDto.getUserAnswer())
                .temporaryUserDto(temporaryUserDto)
                .build();
    }

    @Transactional
    public List<TempUserFinishDataVO> getTempUserScore(String userUuid) {
        validatorUtil.checkValidation(new TempUserVO(userUuid));
        List<TempUserFinishDataVO> finishDataVOList = persistencePortMB.getUserFinishDataFromRoom(userUuid);
        eventPublisherWrapper.publishEvent(new CloseTempUserEvent(this, userUuid, finishDataVOList));
        return finishDataVOList;

    }
}
