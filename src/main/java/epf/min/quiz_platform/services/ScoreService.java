package epf.min.quiz_platform.services;

import epf.min.quiz_platform.DAO.UserDAO;
import epf.min.quiz_platform.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    private final UserDAO userDAO;

    public ScoreService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void updateScore(Long userId, int score) {
        User user = userDAO.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setScore(score);
        userDAO.save(user);
    }
}

