package pl.quiz.domain.dto.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import pl.quiz.domain.dto.vo.TempUserFinishDataVO;

import java.util.List;

@Getter
public class CloseTempUserEvent extends ApplicationEvent {

    private final String userUuid;
    private final List<TempUserFinishDataVO> userFinishDataVOList;

    public CloseTempUserEvent(Object source, String userUuid, List<TempUserFinishDataVO> userFinishDataVOList) {
        super(source);
        this.userUuid = userUuid;
        this.userFinishDataVOList = userFinishDataVOList;
    }
}