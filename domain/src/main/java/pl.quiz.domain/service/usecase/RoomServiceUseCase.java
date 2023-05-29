package pl.quiz.domain.service.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.quiz.domain.dto.*;
import pl.quiz.domain.dto.vo.CreatorRoomVO;
import pl.quiz.domain.dto.vo.TempUserFinishDataVO;
import pl.quiz.domain.port.PersistencePortMB;
import pl.quiz.domain.port.RoomPersistencePort;
import pl.quiz.domain.service.CreatorUserService;
import pl.quiz.domain.service.TemporaryUserService;
import pl.quiz.domain.validator.ValidatorUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public class RoomServiceUseCase {

    private final CreatorUserService creatorUserService;
    private final ScheduledExecutorService scheduledExecutorService;
    private final CloseRoomUseCase closeRoomUseCase;
    private final TemporaryUserService temporaryUserService;
    private final PersistencePortMB persistencePortMB;
    private final RoomPersistencePort roomPersistencePort;
    private final ValidatorUtil validator;

    private long roomId;

    public Long createRoom(RoomDto room, String name) {
        validator.checkValidation(room);
        CreatorUser creatorUser = creatorUserService.getCreatorUser(name);
        Long id = roomId = roomPersistencePort.create(buildCreatorRoomVO(creatorUser, room));

        scheduledExecutorService.schedule(closeRoom(roomId),
                Duration.between(LocalDateTime.now(), room.getEndDate()).toSeconds(),
                TimeUnit.SECONDS);
        return id;
    }

    private CreatorRoomVO buildCreatorRoomVO(CreatorUser creatorUser, RoomDto roomDto) {
        return CreatorRoomVO.builder()
                .creatorUser(creatorUser)
                .roomDto(roomDto)
                .build();
    }


    private Runnable closeRoom(final Long roomId) {
        return () -> {
            log.warn("scheduler {} is executing in thread {}", this.getClass(), Thread.currentThread());
            try {
                temporaryUserService.getTempUserInRoom(roomId).forEach(u -> {
                    List<TempUserFinishDataVO> userFinishData = persistencePortMB.getUserFinishDataFromRoom(u.getUuid());
                    closeRoomUseCase.cleanUpAfterTempUserFinishedRoom(u.getUuid(), userFinishData);
                });
            } catch (Throwable t){
                log.error(t.getMessage());
            }
        };
    }

}
