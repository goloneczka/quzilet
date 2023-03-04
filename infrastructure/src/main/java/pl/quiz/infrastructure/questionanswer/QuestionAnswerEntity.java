package pl.quiz.infrastructure.questionanswer;

import lombok.*;
import pl.quiz.infrastructure.temporaryuser.TemporaryUserEntity;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION_ANSWER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionAnswerEntity {

    private static final String SEQ = "QUESTION_ANSWER_SEQ";

    @Id
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = SEQ)
    private Long id;

    @Column
    private Long userAnswer;

    @Column
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temporary_user_id")
    private TemporaryUserEntity temporaryUser;

}
