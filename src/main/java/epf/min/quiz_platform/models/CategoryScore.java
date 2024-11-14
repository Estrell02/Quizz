package epf.min.quiz_platform.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category_scores")
@Getter
@Setter
@NoArgsConstructor
public class CategoryScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String category;  // Nom de la catégorie du quiz (ex. "Science", "Histoire")

    private int score;  // Score pour cette catégorie
}

