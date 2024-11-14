package epf.min.quiz_platform.services;

import epf.min.quiz_platform.DAO.CategoryScoreDAO;
import epf.min.quiz_platform.DAO.UserDAO;
import epf.min.quiz_platform.models.CategoryScore;
import epf.min.quiz_platform.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryScoreService {

    @Autowired
    private CategoryScoreDAO categoryScoreDAO;

    @Autowired
    private UserDAO userDAO;

    // Méthode pour sauvegarder un score pour un utilisateur et une catégorie
    public void saveCategoryScore(String username, String category, int score) {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CategoryScore categoryScore = new CategoryScore();
        categoryScore.setUser(user);
        categoryScore.setCategory(category);
        categoryScore.setScore(score);

        categoryScoreDAO.save(categoryScore);
    }

    // Méthode pour récupérer tous les scores d'un utilisateur
    public List<CategoryScore> getCategoryScores(String username) {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username " + username + " not found"));

        return categoryScoreDAO.findByUser(user);
    }

    // Méthode pour récupérer les scores d'un utilisateur pour une catégorie spécifique
    public Optional<CategoryScore> getCategoryScoresByCategory(String username, String category) {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return categoryScoreDAO.findByUserAndCategory(user, category);
    }
}

