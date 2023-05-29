package pl.quiz.domain.port;

import pl.quiz.domain.dto.QuestionToAskDto;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.dto.vo.CreatorRoomVO;

public interface RoomPersistencePort {

    Long create(CreatorRoomVO room);

    boolean isRoomExistById(Long id);

    RoomDto getRoomById(Long id);

    QuestionToAskDto getFirstQuestionByRoomId(Long id);

    RoomDto getRoomByQuestionId(Long questionId);
}
