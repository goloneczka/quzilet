package pl.quiz.infrastructure.room;

import org.mapstruct.Mapper;
import pl.quiz.domain.dto.CreatorUser;
import pl.quiz.domain.dto.QuestionToAskDto;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.dto.QuestionDto;
import pl.quiz.domain.dto.vo.CreatorRoomVO;
import pl.quiz.infrastructure.UUIDGeneratorUtil;
import pl.quiz.infrastructure.creatoruser.CreatorUserEntity;
import pl.quiz.infrastructure.question.QuestionEntity;

import java.util.*;


@Mapper(componentModel = "spring")
public
interface RoomMapper {

    QuestionDto questionToDTO(QuestionEntity question);

    QuestionEntity questionToEntity(QuestionDto question);

    QuestionToAskDto questionToDto(QuestionEntity question);

    RoomDto roomToDTO(RoomEntity room);

    default RoomEntity roomToEntity(CreatorRoomVO room) {
        RoomEntity entity = new RoomEntity();
        RoomDto roomDto = room.getRoomDto();

        entity.setName(roomDto.getName());
        entity.setStartDate(roomDto.getStartDate());
        entity.setEndDate(roomDto.getEndDate());
        entity.setUuidPath(UUIDGeneratorUtil.generate64StringUUID());
        entity.setCreatorUser(createCreatorUserEntity(room.getCreatorUser()));

        Set<QuestionEntity> questionEntitySet = new HashSet<>();
        List<QuestionDto> questions = new ArrayList<>(roomDto.getQuestions());

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

    default CreatorUserEntity createCreatorUserEntity(CreatorUser creatorUser) {
        CreatorUserEntity creatorUserEntity = new CreatorUserEntity();
        creatorUserEntity.setId(creatorUser.getId());
        creatorUserEntity.setName(creatorUser.getName());
        return creatorUserEntity;
    }


}
