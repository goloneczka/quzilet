package pl.quiz.infrastructure.room;

import org.mapstruct.Mapper;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.dto.QuestionDto;
import pl.quiz.infrastructure.UUIDGeneratorUtil;

import java.util.*;


@Mapper(componentModel = "spring")
public
interface RoomMapper {

    RoomDto roomToDTO(RoomEntity room);

    QuestionDto QuestionToDTO(QuestionEntity question);

    QuestionEntity questionToEntity(QuestionDto question);

    default RoomEntity roomToEntity(RoomDto room) {
        RoomEntity entity = new RoomEntity();
        entity.setName(room.getName());
        entity.setUuidPath(UUIDGeneratorUtil.generate64StringUUID());

        Set<QuestionEntity> questionEntitySet = new HashSet<>();
        List<QuestionDto> questions = new ArrayList<>(room.getQuestions());

        questionEntitySet.add(saveRoomQuestion(
                entity, questions.listIterator()
        ));
        entity.setQuestions(questionEntitySet);
        return entity;
    }

    default QuestionEntity saveRoomQuestion(RoomEntity entity,
                                            Iterator<QuestionDto> questionDtoIterator) {
        QuestionEntity current = questionToEntity(questionDtoIterator.next());
        current.setUuidPath(UUIDGeneratorUtil.generate64StringUUID());
        current.setRoom(entity);

        if (!questionDtoIterator.hasNext()){
            return current;
        }
        current.setNextQuestion(saveRoomQuestion(entity, questionDtoIterator));
        return current;

    }


}
