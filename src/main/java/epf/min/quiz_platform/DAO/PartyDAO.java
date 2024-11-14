package epf.min.quiz_platform.DAO;
import epf.min.quiz_platform.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyDAO extends JpaRepository<Party, Long> {
}
