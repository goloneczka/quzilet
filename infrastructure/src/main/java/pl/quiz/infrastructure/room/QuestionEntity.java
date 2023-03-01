package pl.quiz.infrastructure.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "room")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "next_question_id")
    private QuestionEntity nextQuestion;


}
