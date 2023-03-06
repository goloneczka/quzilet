package pl.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.quiz.domain.DomainConfig;
import pl.quiz.domain.port.*;
import pl.quiz.domain.service.*;
import pl.quiz.domain.validator.ValidatorUtil;
import pl.quiz.infrastructure.InfrastructureConfig;

@Configuration
@Import({InfrastructureConfig.class, DomainConfig.class})
@RequiredArgsConstructor
public class AppConfig {

    private final RoomPersistencePort roomPersistencePort;
    private final ValidatorUtil validatorUtil;
    private final TemporaryUserPersistencePort temporaryUserPersistencePort;
    private final QuestionPersistencePort questionPersistencePort;
    private final QuestionAnswerPersistencePort questionAnswerPersistencePort;
    private final PersistencePortMB persistencePortMB;

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
    QuestionAnswerService questionAnswerService(TemporaryUserService temporaryUserService) {
        return new QuestionAnswerService(questionAnswerPersistencePort, temporaryUserService,
                validatorUtil, persistencePortMB);
    }

    @Bean
    QuestionService questionService() {
        return new QuestionService(questionPersistencePort, validatorUtil);
    }
}
