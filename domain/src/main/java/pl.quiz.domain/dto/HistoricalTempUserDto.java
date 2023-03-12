package pl.quiz.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.quiz.domain.constraint.IsRoomExist;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class HistoricalTempUserDto {

    private Long id;

    @NotBlank
    private String usernameForHistoricalTempUser;

    private Integer correctAnswers;

    @IsRoomExist
    private Long RoomId;

}
