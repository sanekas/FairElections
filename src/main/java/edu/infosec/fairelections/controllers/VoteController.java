package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.model.entities.CurrentUser;
import edu.infosec.fairelections.services.api.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class VoteController {
    private final VoterService voterService;


    @Autowired
    public VoteController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PreAuthorize("hasAuthority('VOTER')")
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public ModelAndView getLoginPage(@RequestParam CurrentUser currentUser,
                                     @RequestParam Vote vote,
                                     @RequestParam Optional<String> error) {
        return new ModelAndView("vote", "error", error);
    }

}
