package pl.quiz.domain;

import lombok.RequiredArgsConstructor;
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


    public Long openRoomWithQuizIfPossible(RoomDto room){
        //check if user is recognized
        //check if room start time is open
        validator.checkValidation(room);
        return roomPersistencePort.create(room);
    }

}
