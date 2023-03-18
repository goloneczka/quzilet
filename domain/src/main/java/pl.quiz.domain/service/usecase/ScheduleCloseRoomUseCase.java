package pl.quiz.domain.service.usecase;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.dto.vo.TempUserFinishDataVO;
import pl.quiz.domain.port.PersistencePortMB;
import pl.quiz.domain.port.RoomPersistencePort;
import pl.quiz.domain.service.TemporaryUserService;
import pl.quiz.domain.validator.ValidatorUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public class ScheduleCloseRoomUseCase {

    private final ScheduledExecutorService scheduledExecutorService;
    private final CloseRoomUseCase closeRoomUseCase;
    private final TemporaryUserService temporaryUserService;
    private final PersistencePortMB persistencePortMB;
    private final RoomPersistencePort roomPersistencePort;
    private final ValidatorUtil validator;

    private long roomId;

    public Long createRoom(RoomDto room) {
        validator.checkValidation(room);
        Long id = roomId = roomPersistencePort.create(room);
        scheduledExecutorService.schedule(closeRoom(roomId),
                Duration.between(LocalDateTime.now(), room.getEndDate()).toSeconds(),
                TimeUnit.SECONDS);
        return id;
    }


    private Runnable closeRoom(final Long roomId) {
        return () -> {
            log.warn("scheduler {} is executing in thread {}", this.getClass(), Thread.currentThread());
            temporaryUserService.getTempUserInRoom(roomId).forEach(u -> {
                List<TempUserFinishDataVO> userFinishData = persistencePortMB.getUserFinishDataFromRoom(u.getUuid());
                closeRoomUseCase.cleanUpAfterTempUserFinishedRoom(u.getUuid(), userFinishData);
            });
        };
    }

}
