package pl.quiz.infrastructure.creatoruser;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.CreatorUser;
import pl.quiz.domain.dto.CreatorUserDto;
import pl.quiz.domain.dto.vo.CreatorUserAuthVO;
import pl.quiz.domain.port.CreatorUserPersistencePort;


@AllArgsConstructor
public class CreatorUserPersistenceAdapter implements CreatorUserPersistencePort {

    private final CreatorUserRepository repository;
    private final CreatorUserMapper mapper;


    @Override
    public long create(CreatorUserDto dto) {
        CreatorUserEntity entity = mapper.toEntity(dto);
        return repository.save(entity).getId();
    }

    @Override
    public CreatorUser get(String name) {
        CreatorUserEntity entity = repository.getByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Creator User with given id didnt find"));
        return mapper.toDto(entity);
    }

    @Override
    public CreatorUserAuthVO getForAuth(String name) {
        CreatorUserEntity entity = repository.getByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Creator User with given id didnt find"));
        return mapper.toAuthVO(entity);
    }

}
