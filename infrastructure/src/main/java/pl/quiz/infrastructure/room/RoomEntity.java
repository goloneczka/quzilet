package pl.quiz.infrastructure.room;

import lombok.*;
import pl.quiz.infrastructure.creatoruser.CreatorUserEntity;
import pl.quiz.infrastructure.question.QuestionEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ROOM")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "questions")
@EqualsAndHashCode(exclude = "questions")
public class RoomEntity {

    private static final String SEQ = "ROOM_SEQ";

    @Id
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = SEQ)
    private Long id;

    @Column
    private String uuidPath;

    @Column
    private String name;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    private Set<QuestionEntity> questions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private CreatorUserEntity creatorUser;

}
