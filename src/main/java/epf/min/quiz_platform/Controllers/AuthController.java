package epf.min.quiz_platform.Controllers;

import epf.min.quiz_platform.DTO.UserDTO;
import epf.min.quiz_platform.models.CategoryScore;
import epf.min.quiz_platform.models.User;
import epf.min.quiz_platform.security.JWTUtil;
import epf.min.quiz_platform.services.CategoryScoreService;
import epf.min.quiz_platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CategoryScoreService categoryScoreService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (token != null) {
            return ResponseEntity.ok().body("Bearer " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/update-category-score")
    public ResponseEntity<?> updateCategoryScore(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> request) {

        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        String category = (String) request.get("category");
        int score = (int) request.get("score");

        userService.updateScoreForCategory(username, category, score);

        return ResponseEntity.ok("Score updated successfully for category: " + category);
    }

    @GetMapping("/my-scores")
    public ResponseEntity<List<CategoryScore>> getMyScores() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        System.out.println("Logged-in username: " + username);
        List<CategoryScore> scores = categoryScoreService.getCategoryScores(username);
        return ResponseEntity.ok(scores);
    }
}
