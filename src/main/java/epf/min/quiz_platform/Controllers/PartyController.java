package epf.min.quiz_platform.Controllers;

import epf.min.quiz_platform.models.Party;
import epf.min.quiz_platform.services.PartyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/party")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @PostMapping("/create")
    public Party createParty(@RequestParam Long hostUserId, @RequestBody List<Long> playerIds) {
        return partyService.createParty(hostUserId, playerIds);
    }
}

