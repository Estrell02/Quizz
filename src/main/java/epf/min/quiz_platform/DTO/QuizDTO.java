package epf.min.quiz_platform.DTO;

import lombok.Data;
import java.util.List;

@Data
public class QuizDTO {
    private Long id;
    private String level;
    private String badges;
    private List<QuestionDTO> questions;
}