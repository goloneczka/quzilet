package pl.quiz.domain.service;

import lombok.RequiredArgsConstructor;
import pl.quiz.domain.dto.vo.OpenRoomVO;
import pl.quiz.domain.dto.QuestionToAskDto;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.port.RoomPersistencePort;
import pl.quiz.domain.validator.ValidatorUtil;

@RequiredArgsConstructor
public class RoomService {

    private final RoomPersistencePort roomPersistencePort;
    private final ValidatorUtil validator;

    public Long createRoom(RoomDto room){
        validator.checkValidation(room);
        return roomPersistencePort.create(room);
    }

    public QuestionToAskDto openRoomWithQuizIfPossible(OpenRoomVO openRoomVO) {
        validator.checkValidation(openRoomVO);
        return getFirstQuestionByRoomId(openRoomVO.getRoomId());
    }

    public boolean isRoomExistById(Long id){
        return roomPersistencePort.isRoomExistById(id);
    }

    public RoomDto getRoom(Long id){
        return roomPersistencePort.getRoomById(id);
    }

    private QuestionToAskDto getFirstQuestionByRoomId(Long id){
        return roomPersistencePort.getFirstQuestionByRoomId(id);
    }

}
