package pl.quiz.infrastructure.questionanswer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.quiz.domain.dto.QuestionAnswer;
import pl.quiz.domain.dto.QuestionAnswerDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserEntity;


@Mapper(componentModel = "spring")
public interface QuestionAnswerMapper {

    @Mapping(target="temporaryUserDto", source="entity.temporaryUser")
    QuestionAnswer toDto(QuestionAnswerEntity entity);

    @Mapping(target="temporaryUser", source="dto.temporaryUserDto")
    QuestionAnswerEntity toEntity(QuestionAnswer dto);



}
