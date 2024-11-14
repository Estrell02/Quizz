package epf.min.quiz_platform.services;

import epf.min.quiz_platform.DAO.UserDAO;
import epf.min.quiz_platform.models.User;
import epf.min.quiz_platform.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JWTUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(String username, String password) {
        Optional<User> userOptional = userDAO.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Générer le token JWT
                return jwtUtil.generateToken(user.getUsername());
            }
        }
        return null; // Retourne null si l'authentification échoue
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }
}
