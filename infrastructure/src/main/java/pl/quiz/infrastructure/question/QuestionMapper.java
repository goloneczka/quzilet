package pl.quiz.infrastructure.question;

import org.mapstruct.Mapper;
import pl.quiz.domain.dto.QuestionToAskDto;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.dto.QuestionDto;
import pl.quiz.infrastructure.UUIDGeneratorUtil;
import pl.quiz.infrastructure.room.RoomEntity;

import java.util.*;


@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto questionToDTO(QuestionEntity question);

    QuestionToAskDto questionToDto(QuestionEntity question);

}
