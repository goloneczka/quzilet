package pl.quiz.infrastructure.temporaryuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.quiz.infrastructure.room.RoomEntity;

import javax.persistence.*;


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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private RoomEntity room;
}
