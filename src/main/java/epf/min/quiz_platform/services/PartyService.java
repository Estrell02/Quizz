package epf.min.quiz_platform.services;

import epf.min.quiz_platform.models.Party;
import epf.min.quiz_platform.models.User;
import epf.min.quiz_platform.DAO.PartyDAO;
import epf.min.quiz_platform.DAO.UserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartyService {

    private final PartyDAO partyDAO;
    private final UserDAO userDAO;

    public PartyService(PartyDAO partyDAO, UserDAO userDAO) {
        this.partyDAO = partyDAO;
        this.userDAO = userDAO;
    }

    @Transactional
    public Party createParty(Long hostUserId, List<Long> playerIds) {
        User hostUser = userDAO.findById(hostUserId).orElseThrow(() -> new IllegalArgumentException("Host User not found"));
        List<User> players = userDAO.findAllById(playerIds);

        Party party = new Party();
        party.setHostUser(hostUser);
        party.setPlayers(players);

        return partyDAO.save(party);
    }
}
