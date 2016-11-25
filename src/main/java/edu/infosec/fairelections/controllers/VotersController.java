package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.services.api.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/voters")
    public ModelAndView getUsersPage() {
        return new ModelAndView("voters", "voters", voterService.getAllVoters());
    }

}