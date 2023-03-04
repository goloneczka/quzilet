package pl.quiz.infrastructure.questionanswer;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.quiz.infrastructure.room.QuestionEntity;
import pl.quiz.infrastructure.room.RoomEntity;

import java.util.Optional;

@Repository
@Transactional
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswerEntity, Long> {

    Optional<QuestionAnswerEntity> findById(Long id);

}
