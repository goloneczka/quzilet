package pl.quiz.infrastructure.room;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.quiz.infrastructure.question.QuestionEntity;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    Optional<RoomEntity> findById(Long id);

    @Query("select e from QuestionEntity e " +
            "join e.room r " +
            "where r.id = :id " +
            "order by e.id ")
    Optional<QuestionEntity> getFirstQuestionByRoomId(Long id, PageRequest page);

    @Query("select e.nextQuestion from QuestionEntity e " +
            "where e.id = :id ")
    Optional<QuestionEntity> getNextQuestionByCurrentId(Long id);
}
