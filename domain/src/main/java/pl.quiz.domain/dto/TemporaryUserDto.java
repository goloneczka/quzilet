package pl.quiz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.quiz.domain.constraint.IsRoomExist;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryUserDto {

    private String uuid;
    @NotBlank
    private String name;
    @IsRoomExist
    private Long roomId;
}
