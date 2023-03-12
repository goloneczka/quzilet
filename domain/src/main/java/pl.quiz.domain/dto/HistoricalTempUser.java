package pl.quiz.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.quiz.domain.constraint.IsRoomExist;
import pl.quiz.domain.dto.RoomDto;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class HistoricalTempUser {

    private Long id;

    private String usernameForHistoricalTempUser;

    private Integer correctAnswers;

    private RoomDto room;

}
