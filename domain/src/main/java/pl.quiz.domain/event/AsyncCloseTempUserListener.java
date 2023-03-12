package pl.quiz.domain.event;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.quiz.domain.dto.HistoricalTempUserDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.service.HistoricalTemporaryUserService;
import pl.quiz.domain.service.TemporaryUserService;

import java.util.Objects;

@EnableAsync
@AllArgsConstructor
public class AsyncCloseTempUserListener {

    HistoricalTemporaryUserService historicalTemporaryUserService;
    TemporaryUserService temporaryUserService;

    @Async
    @EventListener
    @Transactional // TODO verify is transactional
    public void cleanUpAfterTempUserFinishedRoom(CloseTempUserEvent closeTempUser) {

        TemporaryUserDto temporaryUserDto = temporaryUserService.getTempUser(closeTempUser.getUserUuid());
        // add to end room report
        Integer sumOfPoints = closeTempUser.getUserFinishDataVOList()
                .stream()
                .filter(v -> !Objects.isNull(v.getUserAnswer()))
                .mapToInt(v -> v.getUserAnswer().equals(v.getCorrectAnswerNumber()) ? 1 : 0)
                .reduce(0, Integer::sum);

        HistoricalTempUserDto historicalTempUserDto = HistoricalTempUserDto.builder()
                .usernameForHistoricalTempUser(temporaryUserDto.getName())
                .RoomId(temporaryUserDto.getRoomId())
                .correctAnswers(Objects.isNull(sumOfPoints) ? 0 : sumOfPoints)
                .build();

        historicalTemporaryUserService.createHistTempUser(historicalTempUserDto);

        // remove user from db
        temporaryUserService.deleteTempUser(temporaryUserDto);
    }
}
