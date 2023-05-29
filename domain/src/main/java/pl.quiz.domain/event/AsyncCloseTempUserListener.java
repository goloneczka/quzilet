package pl.quiz.domain.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;

import pl.quiz.domain.service.usecase.CloseRoomUseCase;


@AllArgsConstructor
@Slf4j
public class AsyncCloseTempUserListener {

    CloseRoomUseCase closeRoomUseCase;

    @Async("asyncExecutor")
    @TransactionalEventListener
    public void cleanUpAfterTempUserFinishedRoom(CloseTempUserEvent closeTempUser) {
        log.warn("listener {} is executing in thread {}", this.getClass(), Thread.currentThread());
        closeRoomUseCase.cleanUpAfterTempUserFinishedRoom(closeTempUser.getUserUuid(), closeTempUser.getUserFinishDataVOList());
    }
}
