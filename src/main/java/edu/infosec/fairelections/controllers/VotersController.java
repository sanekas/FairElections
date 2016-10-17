package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VotersController {

    private final VoterService voterService;

    @Autowired
    public VotersController(VoterService voterService) {
        this.voterService = voterService;
    }

    @RequestMapping("/voters")
    public ModelAndView getVotersPage() {
        return new ModelAndView("voters", "voters", voterService.getAllVoters());
    }

}