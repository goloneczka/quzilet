package pl.quiz.infrastructure.question;

import lombok.*;
import pl.quiz.infrastructure.room.RoomEntity;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"room", "nextQuestion"})
@EqualsAndHashCode(exclude = {"room", "nextQuestion"})
public class QuestionEntity {

    private static final String SEQ = "QUESTION_SEQ";

    @Id
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = SEQ)
    private Long id;

    @Column
    private String uuidPath;

    @Column
    private String name;

    @Column
    private String ask;

    @Column
    private String ask2;

    @Column
    private String ask3;

    @Column
    private String ask4;

    @Column(name = "correct_answ_number")
    private Integer correctAnswerNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "next_question_id")
    private QuestionEntity nextQuestion;


}
