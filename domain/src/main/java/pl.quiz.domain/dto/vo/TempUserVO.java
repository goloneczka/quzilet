package pl.quiz.domain.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.quiz.domain.constraint.IsTempUserExist;

@Data
@AllArgsConstructor
public class TempUserVO {
    @IsTempUserExist
    private String userUuid;
}
