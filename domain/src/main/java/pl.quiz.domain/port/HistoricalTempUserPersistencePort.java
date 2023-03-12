package pl.quiz.domain.port;

import pl.quiz.domain.dto.HistoricalTempUser;
import pl.quiz.domain.dto.HistoricalTempUserDto;


public interface HistoricalTempUserPersistencePort {

    HistoricalTempUserDto get(Long id);

    Long create(HistoricalTempUser dto);

}
