package pl.quiz.domain.port;

import pl.quiz.domain.dto.QuestionAnswerDto;
import pl.quiz.domain.dto.TemporaryUserDto;


public interface TemporaryUserPersistencePort {

    Long create(TemporaryUserDto room);

    TemporaryUserDto get(String uuid);

    boolean isExist(String uuid);

    Long delete(TemporaryUserDto uuid);
}
