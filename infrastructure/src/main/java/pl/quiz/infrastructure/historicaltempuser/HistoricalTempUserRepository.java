package pl.quiz.infrastructure.historicaltempuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HistoricalTempUserRepository extends JpaRepository<HistoricalTempUserEntity, Long> {


}
