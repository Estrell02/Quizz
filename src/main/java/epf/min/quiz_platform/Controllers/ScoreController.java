package epf.min.quiz_platform.Controllers;

import epf.min.quiz_platform.DAO.PartyDAO;
import epf.min.quiz_platform.models.User;
import epf.min.quiz_platform.models.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final PartyDAO partyDAO;

    @Autowired
    public ScoreController(PartyDAO partyDAO) {
        this.partyDAO = partyDAO;
    }

    @GetMapping("/party/{partyId}")
    public List<User> getScoresForParty(@PathVariable Long partyId) {
        // Récupère la partie par ID, puis les utilisateurs associés
        Party party = partyDAO.findById(partyId).orElse(null);

        if (party == null) {
            throw new RuntimeException("Party not found with ID: " + partyId);
        }

        return party.getPlayers(); // Retourne la liste des utilisateurs de la partie
    }
}
