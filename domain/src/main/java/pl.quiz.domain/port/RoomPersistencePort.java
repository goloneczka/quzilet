package pl.quiz.domain.port;

import pl.quiz.domain.dto.RoomDto;

public interface RoomPersistencePort {

    Long create(RoomDto room);

    boolean isRoomExistById(Long id);

    RoomDto getRoomById(Long id);
}
