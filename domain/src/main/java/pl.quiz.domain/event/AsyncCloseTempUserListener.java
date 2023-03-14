package pl.quiz.domain.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import pl.quiz.domain.dto.HistoricalTempUserDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.service.HistoricalTemporaryUserService;
import pl.quiz.domain.service.TemporaryUserService;

import java.util.Objects;
import java.util.concurrent.Executor;

@AllArgsConstructor
@Slf4j
public class AsyncCloseTempUserListener implements AsyncConfigurer {

    HistoricalTemporaryUserService historicalTemporaryUserService;
    TemporaryUserService temporaryUserService;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(5);
        executor.initialize();
        executor.setThreadNamePrefix("async-listener-");
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }


    @Async
    @TransactionalEventListener
    @Transactional
    public void cleanUpAfterTempUserFinishedRoom(CloseTempUserEvent closeTempUser) {

        log.warn("listener {} is executing in thread {}", this.getClass(), Thread.currentThread());

        TemporaryUserDto temporaryUserDto = temporaryUserService.getTempUser(closeTempUser.getUserUuid());
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

        temporaryUserService.deleteTempUser(temporaryUserDto);
    }
}
