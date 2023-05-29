package pl.quiz.infrastructure.creatoruser;

import org.mapstruct.Mapper;
import pl.quiz.domain.dto.CreatorUser;
import pl.quiz.domain.dto.CreatorUserDto;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.dto.vo.CreatorUserAuthVO;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserEntity;

@Mapper(componentModel = "spring")
public interface CreatorUserMapper {

    CreatorUser toDto(CreatorUserEntity creatorUserEntity);

    CreatorUserEntity toEntity(CreatorUserDto creatorUserDto);

    CreatorUserAuthVO toAuthVO(CreatorUserEntity creatorUserDto);

}
