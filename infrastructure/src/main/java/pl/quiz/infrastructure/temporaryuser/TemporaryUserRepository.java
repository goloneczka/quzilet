package pl.quiz.infrastructure.temporaryuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface TemporaryUserRepository extends JpaRepository<TemporaryUserEntity, Long> {

    Optional<TemporaryUserEntity> getByUuid(String uuid);

    boolean existsByUuid(String uuid);

    List<TemporaryUserEntity> getAllByRoomId(long roomId);
}
