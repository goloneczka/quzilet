package pl.quiz.infrastructure.creatoruser;


import lombok.*;
import pl.quiz.infrastructure.questionanswer.QuestionAnswerEntity;
import pl.quiz.infrastructure.room.RoomEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CREATOR_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "rooms")
@EqualsAndHashCode(exclude = "rooms")
public class CreatorUserEntity {

    private static final String SEQ = "CREATOR_USER_SEQ";

    @Id
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = SEQ)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "creatorUser", orphanRemoval = true)
    private Set<RoomEntity> rooms;
}
