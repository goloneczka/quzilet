package pl.quiz.infrastructure.room;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.port.RoomPersistencePort;

@AllArgsConstructor
public class RoomPersistenceAdapter implements RoomPersistencePort {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;


    public Long create(RoomDto room) {
        RoomEntity entity = roomMapper.roomToEntity(room);
        return roomRepository.save(entity).getId();
    }

}
