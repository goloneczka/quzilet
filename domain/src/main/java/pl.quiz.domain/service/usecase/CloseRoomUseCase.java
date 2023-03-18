package pl.quiz.domain.service.usecase;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import pl.quiz.domain.dto.HistoricalTempUserDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.dto.vo.TempUserFinishDataVO;
import pl.quiz.domain.service.HistoricalTemporaryUserService;
import pl.quiz.domain.service.TemporaryUserService;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class CloseRoomUseCase {

    private final HistoricalTemporaryUserService historicalTemporaryUserService;
    private final TemporaryUserService temporaryUserService;


    @Transactional
    public void cleanUpAfterTempUserFinishedRoom(String userUuid, List<TempUserFinishDataVO> userFinishDataVOList) {

        TemporaryUserDto temporaryUserDto = temporaryUserService.getTempUser(userUuid);
        Integer sumOfPoints = userFinishDataVOList
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

        temporaryUserService.deleteTempUser(temporaryUserDto);
    }
}
