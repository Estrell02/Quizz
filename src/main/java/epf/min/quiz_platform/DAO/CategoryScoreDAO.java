package epf.min.quiz_platform.DAO;

import epf.min.quiz_platform.models.CategoryScore;
import epf.min.quiz_platform.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryScoreDAO extends JpaRepository<CategoryScore, Long> {
    Optional<CategoryScore> findByUserAndCategory(User user, String category);
    List<CategoryScore> findByUser(User user);

}
