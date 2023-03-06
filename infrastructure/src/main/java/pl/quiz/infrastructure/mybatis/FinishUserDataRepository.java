package pl.quiz.infrastructure.mybatis;

import org.apache.ibatis.annotations.*;
import pl.quiz.domain.dto.vo.TempUserFinishDataVO;

import java.util.List;

@Mapper
public interface FinishUserDataRepository {

    @Select(value = "select q.name as questionName, q.correct_answ_number as correctAnswerNumber, qu.user_answer as userAnswer " +
            "from TEMPORARY_USER tu " +
            "left join QUESTION_ANSWER qu on tu.id = qu.temporary_user_id " +
            "left join QUESTION q on qu.question_id = q.id " +
            "where tu.uuid = #{userUuid}")
    List<TempUserFinishDataVO> getUserFinishDataFromRoom(String userUuid);
}
