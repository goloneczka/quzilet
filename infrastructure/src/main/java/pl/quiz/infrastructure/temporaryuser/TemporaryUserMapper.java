package pl.quiz.infrastructure.temporaryuser;

import org.mapstruct.Mapper;
import pl.quiz.domain.dto.TemporaryUserDto;

@Mapper(componentModel = "spring")
public interface TemporaryUserMapper {

    TemporaryUserDto toDto(TemporaryUserEntity question);

    TemporaryUserEntity toEntity(TemporaryUserDto question);



}
