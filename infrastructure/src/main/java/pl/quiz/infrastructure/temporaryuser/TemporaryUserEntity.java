package pl.quiz.infrastructure.temporaryuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.quiz.infrastructure.questionanswer.QuestionAnswerEntity;
import pl.quiz.infrastructure.room.QuestionEntity;
import pl.quiz.infrastructure.room.RoomEntity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "TEMPORARY_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryUserEntity {

    private static final String SEQ = "TEMPORARY_USER_SEQ";

    @Id
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = SEQ)
    private Long id;

    @Column
    private String uuid;

    @Column
    private String name;

    @Column
    private String sillyPassword;

    @Column
    private Long roomId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "temporaryUser")
    private Set<QuestionAnswerEntity> questionAnswers;
}
