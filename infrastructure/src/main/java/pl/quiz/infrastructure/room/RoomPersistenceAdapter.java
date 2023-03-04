package pl.quiz.infrastructure.room;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import pl.quiz.domain.dto.QuestionToAskDto;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.port.RoomPersistencePort;

import java.util.Objects;

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

    @Override
    public QuestionToAskDto getFirstQuestionByRoomId(Long id) {
        return mapper.questionToDto(
                repository.getFirstQuestionByRoomId(id, PageRequest.of(0, 1))
                        .orElseThrow(() -> new IllegalArgumentException("Question for room didnt find"))
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
