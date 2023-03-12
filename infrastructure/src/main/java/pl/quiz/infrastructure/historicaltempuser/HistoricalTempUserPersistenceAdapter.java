package pl.quiz.infrastructure.historicaltempuser;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.HistoricalTempUser;
import pl.quiz.domain.dto.HistoricalTempUserDto;
import pl.quiz.domain.port.HistoricalTempUserPersistencePort;


@AllArgsConstructor
public class HistoricalTempUserPersistenceAdapter implements HistoricalTempUserPersistencePort {

    private final HistoricalTempUserRepository repository;
    private final HistoricalTempUserMapper mapper;

    @Override
    public HistoricalTempUserDto get(Long id) {
        HistoricalTempUserEntity entity =  repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Historical Temp User with given uuid didnt find"));

        return mapper.toDto(entity);
    }

    @Override
    public Long create(HistoricalTempUser dto) {
        HistoricalTempUserEntity entity = mapper.toEntity(dto);
        return repository.save(entity).getId();
    }
}
