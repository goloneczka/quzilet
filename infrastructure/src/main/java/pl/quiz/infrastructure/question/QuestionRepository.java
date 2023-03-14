package pl.quiz.infrastructure.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<pl.quiz.infrastructure.question.QuestionEntity, Long> {

    @Query("select e.nextQuestion from QuestionEntity e " +
            "where e.id = :id ")
    Optional<pl.quiz.infrastructure.question.QuestionEntity> getNextQuestionByCurrentId(Long id);

}
