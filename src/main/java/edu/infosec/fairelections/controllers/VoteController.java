package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.model.entities.CurrentUser;
import edu.infosec.fairelections.model.entities.VoterForm;
import edu.infosec.fairelections.services.api.CandidatesService;
import edu.infosec.fairelections.services.api.VoterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class VoteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteController.class);

    private final VoterService voterService;
    private final CandidatesService candidatesService;

    @Autowired
    public VoteController(VoterService voterService, CandidatesService candidatesService) {
        this.voterService = voterService;
        this.candidatesService = candidatesService;
    }


    @PreAuthorize("hasAuthority('VOTER') || hasAuthority('ADMIN')")
    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public ModelAndView getVoterPage() {

        ModelAndView voterModelAndView = new ModelAndView();

        voterModelAndView.addObject("form", new VoterForm());
        voterModelAndView.addObject("candidates", candidatesService.getCandidates());
        voterModelAndView.setViewName("vote");

        return voterModelAndView;
    }

    @PreAuthorize("hasAuthority('VOTER') || hasAuthority('ADMIN')")
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public String handleVoterCreateForm(@ModelAttribute VoterForm voterForm, Authentication authentication,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        CurrentUser currentUser= (CurrentUser) authentication.getPrincipal();
        try {
            Vote vote = Vote.valueOf(voterForm.getVote());
            if (vote != Vote.EMPTY) {
                voterService.save(currentUser.getId(), vote);
            }
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Voting is failed, voter: " + currentUser + ", voterForm: " + voterForm);
            bindingResult.reject("voter.id");
            return "error";
        }
        LOGGER.info("Voter: " + currentUser + " voted, voterForm: " + voterForm);
        return "home";
    }

}
