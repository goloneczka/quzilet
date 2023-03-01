package pl.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.quiz.domain.DomainConfig;
import pl.quiz.domain.RoomService;
import pl.quiz.domain.port.RoomPersistencePort;
import pl.quiz.domain.validator.ValidatorUtil;
import pl.quiz.infrastructure.InfrastructureConfig;

@Configuration
@Import({InfrastructureConfig.class, DomainConfig.class})
@RequiredArgsConstructor
public class AppConfig {

    private final RoomPersistencePort roomPersistencePort;
    private final ValidatorUtil validatorUtil;

    @Bean
    RoomService roomService(){
        return new RoomService(roomPersistencePort, validatorUtil);
    }
}
