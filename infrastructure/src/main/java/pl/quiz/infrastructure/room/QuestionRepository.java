package pl.quiz.infrastructure.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query("select e.nextQuestion from QuestionEntity e " +
            "where e.id = :id ")
    Optional<QuestionEntity> getNextQuestionByCurrentId(Long id);

}
