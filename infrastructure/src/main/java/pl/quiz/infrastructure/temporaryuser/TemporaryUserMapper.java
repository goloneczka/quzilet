package pl.quiz.infrastructure.temporaryuser;

import org.mapstruct.Mapper;
import pl.quiz.domain.dto.QuestionDto;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.infrastructure.UUIDGeneratorUtil;
import pl.quiz.infrastructure.room.QuestionEntity;
import pl.quiz.infrastructure.room.RoomEntity;

import java.util.*;


@Mapper(componentModel = "spring")
public interface TemporaryUserMapper {

    TemporaryUserDto toDto(TemporaryUserEntity question);

    TemporaryUserEntity toEntity(TemporaryUserDto question);



}
