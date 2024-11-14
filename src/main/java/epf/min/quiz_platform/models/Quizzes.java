package epf.min.quiz_platform.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;
import epf.min.quiz_platform.models.*;
@Entity
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
public class Quizzes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Questions> questions;
    @OneToOne
    @JoinColumn(name = "player_id")
    private User player;


}



