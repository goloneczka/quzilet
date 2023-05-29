package pl.quiz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.quiz.domain.constraint.IsRoomExist;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatorUserDto {

    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
