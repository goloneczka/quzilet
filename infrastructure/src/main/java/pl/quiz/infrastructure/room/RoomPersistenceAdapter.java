package pl.quiz.infrastructure.room;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.port.RoomPersistencePort;

@AllArgsConstructor
public class RoomPersistenceAdapter implements RoomPersistencePort {

    private final RoomRepository repository;
    private final RoomMapper mapper;

    public RoomDto getRoomById(Long id) {
        return mapper.roomToDTO(
                repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room with given Id didnt find"))
        );
    }

    public boolean isRoomExistById(Long id) {
        return repository.existsById(id);
    }

    public Long create(RoomDto room) {
        RoomEntity entity = mapper.roomToEntity(room);
        return repository.save(entity).getId();
    }

}
