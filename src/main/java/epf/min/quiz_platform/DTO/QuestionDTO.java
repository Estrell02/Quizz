package epf.min.quiz_platform.DTO;

import lombok.Data;
import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String text;
    private String type;
    private String categorie;
    private Long quizId;

}
