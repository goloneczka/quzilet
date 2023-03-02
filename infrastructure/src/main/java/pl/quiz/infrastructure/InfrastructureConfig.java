package pl.quiz.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.quiz.infrastructure.room.RoomMapper;
import pl.quiz.infrastructure.room.RoomPersistenceAdapter;
import pl.quiz.infrastructure.room.RoomRepository;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserMapper;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserPersistenceAdapter;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserRepository;

@Configuration
public class InfrastructureConfig {

    @Bean
    RoomPersistenceAdapter roomPersistenceAdapter(RoomMapper roomMapper,
                                                  RoomRepository roomRepository) {
        return new RoomPersistenceAdapter(roomRepository, roomMapper);
    }

    @Bean
    TemporaryUserPersistenceAdapter temporaryUserPersistenceAdapter(TemporaryUserMapper mapper,
                                                                    TemporaryUserRepository repository) {
        return new TemporaryUserPersistenceAdapter(repository, mapper);
    }
}
