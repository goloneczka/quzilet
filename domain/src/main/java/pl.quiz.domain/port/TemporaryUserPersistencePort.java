package pl.quiz.domain.port;

import pl.quiz.domain.dto.TemporaryUserDto;
import java.util.List;


public interface TemporaryUserPersistencePort {

    String create(TemporaryUserDto room);

    TemporaryUserDto get(String uuid);

    boolean isExist(String uuid);

    Long delete(TemporaryUserDto uuid);

    List<TemporaryUserDto> getTempUsersInRoom(Long roomId);
}
