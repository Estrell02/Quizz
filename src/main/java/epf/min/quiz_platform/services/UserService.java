package epf.min.quiz_platform.services;

import epf.min.quiz_platform.DAO.CategoryScoreDAO;
import epf.min.quiz_platform.DAO.UserDAO;
import epf.min.quiz_platform.DTO.UserDTO;
import epf.min.quiz_platform.models.CategoryScore;
import epf.min.quiz_platform.models.User;
import epf.min.quiz_platform.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CategoryScoreDAO categoryScoreDAO;

    @Autowired
    private JWTUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(String username, String password) {
        Optional<User> userOptional = userDAO.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(user.getUsername());
            } else {
                System.out.println("Invalid password for user: " + username); // Ajout d'un log
            }
        } else {
            System.out.println("User not found: " + username); // Ajout d'un log
        }
        return null; // Retourne null si l'authentification échoue
    }


    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.findAll(); // Récupère tous les utilisateurs de la base de données
        List<UserDTO> userDTOs = new ArrayList<>();

        // Convertir les entités User en UserDTO
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setScore(user.getScore());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
    public void updateScoreForCategory(String username, String category, int score) {
        User user = userDAO.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        CategoryScore categoryScore = categoryScoreDAO.findByUserAndCategory(user, category)
                .orElse(new CategoryScore());

        categoryScore.setUser(user);
        categoryScore.setCategory(category);
        categoryScore.setScore(score);

        categoryScoreDAO.save(categoryScore);
    }

    public List<CategoryScore> getCategoryScores(String username) {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return categoryScoreDAO.findByUser(user);
    }


}
