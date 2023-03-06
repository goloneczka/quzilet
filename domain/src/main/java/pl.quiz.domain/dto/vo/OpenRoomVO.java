package pl.quiz.domain.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.quiz.domain.constraint.IsRoomAlreadyOpen;
import pl.quiz.domain.constraint.IsRoomExist;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IsRoomAlreadyOpen
public class OpenRoomVO {

    @IsRoomExist
    private Long roomId;
    private LocalDateTime currentTime;
}
