package pl.quiz.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.quiz.infrastructure.mybatis.FinishUserDataRepository;
import pl.quiz.infrastructure.mybatis.PersistenceAdapterMB;
import pl.quiz.infrastructure.question.QuestionMapper;
import pl.quiz.infrastructure.question.QuestionPersistenceAdapter;
import pl.quiz.infrastructure.question.QuestionRepository;
import pl.quiz.infrastructure.questionanswer.QuestionAnswerMapper;
import pl.quiz.infrastructure.questionanswer.QuestionAnswerPersistenceAdapter;
import pl.quiz.infrastructure.questionanswer.QuestionAnswerRepository;
import pl.quiz.infrastructure.room.*;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserMapper;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserPersistenceAdapter;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserRepository;

@Configuration
public class InfrastructureConfig {

    @Bean
    RoomPersistenceAdapter roomPersistenceAdapter(RoomMapper roomMapper,
                                                  RoomRepository roomRepository) {
        return new RoomPersistenceAdapter(roomRepository, roomMapper);
    }

    @Bean
    QuestionPersistenceAdapter questionPersistencePort(QuestionMapper questionMapper,
                                                       QuestionRepository questionRepository) {
        return new QuestionPersistenceAdapter(questionRepository, questionMapper);
    }

    @Bean
    TemporaryUserPersistenceAdapter temporaryUserPersistenceAdapter(TemporaryUserMapper mapper,
                                                                    TemporaryUserRepository repository) {
        return new TemporaryUserPersistenceAdapter(repository, mapper);
    }

    @Bean
    QuestionAnswerPersistenceAdapter questionAnswerPersistenceAdapter(QuestionAnswerMapper mapper,
                                                                      QuestionAnswerRepository repository) {
        return new QuestionAnswerPersistenceAdapter(repository, mapper);
    }

    @Bean
    PersistenceAdapterMB persistenceAdapterMB(FinishUserDataRepository finishUserDataRepository){
        return new PersistenceAdapterMB(finishUserDataRepository);
    }
}
