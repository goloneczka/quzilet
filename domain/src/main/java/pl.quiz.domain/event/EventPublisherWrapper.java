package pl.quiz.domain.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;


@AllArgsConstructor
@Slf4j
public class EventPublisherWrapper {

    ApplicationEventPublisher publisher;

    public void publishEvent(ApplicationEvent publishedObject){
        log.info("Publisher: {} is publishing: {} in thread: {}", getPublisherName(publishedObject), publishedObject, Thread.currentThread());
        publisher.publishEvent(publishedObject);
    }

    private String getPublisherName(ApplicationEvent publishedObject){
        return publishedObject.getSource().getClass().getSimpleName();
    }
}
