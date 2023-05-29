package pl.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pl.quiz.domain.DomainConfig;
import pl.quiz.domain.event.AsyncCloseTempUserListener;
import pl.quiz.domain.event.EventPublisherWrapper;
import pl.quiz.domain.port.*;
import pl.quiz.domain.service.*;
import pl.quiz.domain.service.auth.CreatorUserAuthService;
import pl.quiz.domain.service.auth.TemporaryUserAuthService;
import pl.quiz.domain.service.usecase.CloseRoomUseCase;
import pl.quiz.domain.service.usecase.RoomServiceUseCase;
import pl.quiz.domain.validator.ValidatorUtil;
import pl.quiz.infrastructure.InfrastructureConfig;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@Import({InfrastructureConfig.class, DomainConfig.class})
@RequiredArgsConstructor
public class AppConfig {

    private final RoomPersistencePort roomPersistencePort;
    private final ValidatorUtil validatorUtil;
    private final TemporaryUserPersistencePort temporaryUserPersistencePort;
    private final HistoricalTempUserPersistencePort historicalTempUserPersistencePort;
    private final QuestionPersistencePort questionPersistencePort;
    private final QuestionAnswerPersistencePort questionAnswerPersistencePort;
    private final PersistencePortMB persistencePortMB;
    private final CreatorUserPersistencePort creatorUserPersistencePort;

    @Bean
    RoomService roomService() {
        return new RoomService(roomPersistencePort, validatorUtil);
    }

    @Bean
    TemporaryUserAuthService temporaryUserAuthService() {
        return new TemporaryUserAuthService(temporaryUserPersistencePort);
    }

    @Bean
    TemporaryUserService temporaryUserService() {
        return new TemporaryUserService(temporaryUserPersistencePort, validatorUtil);
    }

    @Bean
    HistoricalTemporaryUserService historicalTemporaryUserService(RoomService roomService) {
        return new HistoricalTemporaryUserService(historicalTempUserPersistencePort, roomService, validatorUtil);
    }

    @Bean
    QuestionAnswerService questionAnswerService(TemporaryUserService temporaryUserService,
                                                EventPublisherWrapper eventPublisherWrapper) {
        return new QuestionAnswerService(questionAnswerPersistencePort, temporaryUserService,
                validatorUtil, persistencePortMB, eventPublisherWrapper);
    }

    @Bean
    QuestionService questionService() {
        return new QuestionService(questionPersistencePort, validatorUtil);
    }

    @Bean
    EventPublisherWrapper eventPublisherWrapper(ApplicationEventPublisher applicationEventPublisher){
        return new EventPublisherWrapper(applicationEventPublisher);
    }

    @Bean
    AsyncCloseTempUserListener asyncCloseTempUserListener(CloseRoomUseCase closeRoomUseCase){
        return new AsyncCloseTempUserListener(closeRoomUseCase);
    }

    @Bean
    ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }

    @Bean
    RoomServiceUseCase scheduleCloseRoomUseCase(ScheduledExecutorService scheduledExecutorService,
                                                TemporaryUserService temporaryUserService,
                                                CloseRoomUseCase closeRoomUseCase,
                                                CreatorUserService creatorUserService){
        return new RoomServiceUseCase(creatorUserService,
                scheduledExecutorService,
                closeRoomUseCase,
                temporaryUserService,
                persistencePortMB,
                roomPersistencePort,
                validatorUtil);
    }

    @Bean
    CreatorUserService creatorUserService(){
        return new CreatorUserService(creatorUserPersistencePort);
    }

    @Bean
    CreatorUserAuthService creatorUserAuthService() {
        return new CreatorUserAuthService(creatorUserPersistencePort);
    }

    @Bean
    CloseRoomUseCase closeRoomUseCase(HistoricalTemporaryUserService historicalTemporaryUserService,
                                      TemporaryUserService temporaryUserService) {
        return new CloseRoomUseCase(historicalTemporaryUserService, temporaryUserService);
    }

    @Bean(name = "asyncExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(5);
        executor.initialize();
        executor.setThreadNamePrefix("custom-async-listener-");
        return executor;
    }

}
