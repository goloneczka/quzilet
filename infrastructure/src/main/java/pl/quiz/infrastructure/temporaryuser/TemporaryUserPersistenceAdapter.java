package pl.quiz.infrastructure.temporaryuser;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.port.RoomPersistencePort;
import pl.quiz.domain.port.TemporaryUserPersistencePort;
import pl.quiz.infrastructure.room.RoomEntity;
import pl.quiz.infrastructure.room.RoomMapper;
import pl.quiz.infrastructure.room.RoomRepository;

import java.util.Optional;

@AllArgsConstructor
public class TemporaryUserPersistenceAdapter implements TemporaryUserPersistencePort {

    private final TemporaryUserRepository repository;
    private final TemporaryUserMapper mapper;


    @Override
    public Long create(TemporaryUserDto room) {
        return null;
    }

    @Override
    public TemporaryUserDto get(String uuid) {
        return mapper.toDto(
                repository.getByUuid(uuid)
                        .orElseThrow(() -> new IllegalArgumentException("Temp User with given uuid didnt find"))
        );
    }
}
