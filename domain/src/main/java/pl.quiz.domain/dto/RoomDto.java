package pl.quiz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Long id;

    @NotBlank
    private String name;

    @NotEmpty
    private Set<@Valid QuestionDto> questions;

    @NotBlank
    private LocalDateTime startDate;

}
