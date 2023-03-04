package pl.quiz.domain.port;

import pl.quiz.domain.dto.QuestionDto;
import pl.quiz.domain.dto.QuestionToAskDto;
import pl.quiz.domain.dto.RoomDto;

public interface RoomPersistencePort {

    Long create(RoomDto room);

    boolean isRoomExistById(Long id);

    RoomDto getRoomById(Long id);

    QuestionToAskDto getFirstQuestionByRoomId(Long id);

}
