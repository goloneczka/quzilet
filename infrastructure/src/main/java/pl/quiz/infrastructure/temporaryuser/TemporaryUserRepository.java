package pl.quiz.infrastructure.temporaryuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface TemporaryUserRepository extends JpaRepository<TemporaryUserEntity, Long> {

    Optional<TemporaryUserEntity> getByUuid(String uuid);
}
