package epf.min.quiz_platform.DTO;

import lombok.Data;
import java.util.List;

@Data
public class PartyDTO {
    private Long id;
    private List<Long> userIds;
    private Long quizId;
    private int score;
}
