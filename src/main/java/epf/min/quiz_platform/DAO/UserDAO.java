package epf.min.quiz_platform.DAO;
import epf.min.quiz_platform.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
