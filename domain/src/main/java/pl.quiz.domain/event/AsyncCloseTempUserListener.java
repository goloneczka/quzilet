package pl.quiz.domain.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import pl.quiz.domain.dto.event.CloseTempUserEvent;

import java.util.Objects;

@EnableAsync
@Component
public class AsyncCloseTempUserListener {

    @Async
    @EventListener
    public void cleanUpAfterTempUserFinishedRoom(CloseTempUserEvent closeTempUser) {
        // add to end room report
        Integer sumOfPoints = closeTempUser.getUserFinishDataVOList()
                .stream()
                .filter(v -> !Objects.isNull(v.getUserAnswer()))
                .mapToInt(v -> v.getUserAnswer().equals(v.getCorrectAnswerNumber()) ? 1 : 0)
                .reduce(0, Integer::sum);
        int maxOfPoints =  closeTempUser.getUserFinishDataVOList().size();
        // remove user from db
        // remove auth
    }
}
