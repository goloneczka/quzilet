package pl.quiz.infrastructure.historicaltempuser;

import org.mapstruct.Mapper;
import pl.quiz.domain.dto.HistoricalTempUser;
import pl.quiz.domain.dto.HistoricalTempUserDto;

@Mapper(componentModel = "spring")
public interface HistoricalTempUserMapper {

    HistoricalTempUserDto toDto(HistoricalTempUserEntity entity);

    HistoricalTempUserEntity toEntity(HistoricalTempUser dto);

}
