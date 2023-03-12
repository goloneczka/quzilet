package pl.quiz.infrastructure.historicaltempuser;

import lombok.*;
import pl.quiz.infrastructure.room.RoomEntity;

import javax.persistence.*;

@Entity
@Table(name = "HISTORICAL_TEMP_USER_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"room"})
@EqualsAndHashCode(exclude = {"room"})
public class HistoricalTempUserEntity {

    private static final String SEQ = "HISTORICAL_TEMP_USER_DATA_SEQ";

    @Id
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = SEQ)
    private Long id;

    @Column(name = "historical_temp_user_name")
    private String usernameForHistoricalTempUser;

    @Column(name = "correct_answers")
    private Integer correctAnswers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private RoomEntity room;

}
