package pl.quiz.domain.dto.vo;

import lombok.Builder;
import lombok.Data;
import pl.quiz.domain.dto.CreatorUser;
import pl.quiz.domain.dto.RoomDto;


@Data
@Builder
public class CreatorRoomVO {

    private RoomDto roomDto;
    private CreatorUser creatorUser;
}
