package pl.quiz.infrastructure.questionanswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.quiz.domain.dto.vo.TempUserFinishDataVO;

import java.util.Optional;
import java.util.List;

@Repository
@Transactional
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswerEntity, Long> {

    Optional<QuestionAnswerEntity> findById(Long id);

}
