package pl.quiz.infrastructure.creatoruser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatorUserRepository extends JpaRepository<CreatorUserEntity, Long> {

    Optional<CreatorUserEntity> getByName(String name);

}
