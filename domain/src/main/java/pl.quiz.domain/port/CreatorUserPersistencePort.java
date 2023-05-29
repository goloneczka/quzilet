package pl.quiz.domain.port;

import pl.quiz.domain.dto.CreatorUser;
import pl.quiz.domain.dto.CreatorUserDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.dto.vo.CreatorUserAuthVO;

import java.util.List;


public interface CreatorUserPersistencePort {

    long create(CreatorUserDto room);

    CreatorUser get(String name);

    CreatorUserAuthVO getForAuth(String name);

}
