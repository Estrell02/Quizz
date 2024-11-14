package epf.min.quiz_platform.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String difficulty;
    private String text;
    private String type;
    private String category;
    private String correct_answer;

    @ElementCollection
    @CollectionTable(name = "incorrect_answers", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "incorrect_answer")
    private List<String> incorrectAnswers;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quizzes quiz;

}

