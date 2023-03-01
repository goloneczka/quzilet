package pl.quiz.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.quiz.infrastructure.room.RoomMapper;
import pl.quiz.infrastructure.room.RoomPersistenceAdapter;
import pl.quiz.infrastructure.room.RoomRepository;

@Configuration
public class InfrastructureConfig {

    @Bean
    RoomPersistenceAdapter roomPersistenceAdapter(RoomMapper roomMapper,
                                                  RoomRepository roomRepository) {
        return new RoomPersistenceAdapter(roomRepository, roomMapper);
    }
}
