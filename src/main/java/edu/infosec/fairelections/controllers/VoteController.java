package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.controllers.exceptions.ElectionsStateException;
import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.model.entities.impl.CurrentUser;
import edu.infosec.fairelections.model.entities.forms.VoterForm;
import edu.infosec.fairelections.services.api.CandidatesService;
import edu.infosec.fairelections.services.api.ElectionsState;
import edu.infosec.fairelections.services.api.ElectionsStateService;
import edu.infosec.fairelections.services.api.VoterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class VoteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteController.class);

    private final VoterService voterService;
    private final CandidatesService candidatesService;
    private final ElectionsStateService stateService;

    @Autowired
    public VoteController(VoterService voterService, CandidatesService candidatesService,
                          ElectionsStateService stateService) {
        this.voterService = voterService;
        this.candidatesService = candidatesService;
        this.stateService = stateService;
    }


    @PreAuthorize("hasAuthority('VOTER')")
    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public ModelAndView getVoterPage() {

        if (stateService.getState() != ElectionsState.RUNNING) {
            throw new ElectionsStateException();
        }
        ModelAndView voterModelAndView = new ModelAndView();

        voterModelAndView.addObject("form", new VoterForm());
        voterModelAndView.addObject("candidates", candidatesService.getCandidates());
        voterModelAndView.setViewName("vote");

        return voterModelAndView;
    }

    @PreAuthorize("hasAuthority('VOTER')")
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public String handleVoterCreateForm(@ModelAttribute VoterForm voterForm, Authentication authentication,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        if (stateService.getState() != ElectionsState.RUNNING) {
            throw new ElectionsStateException();
        }
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
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
        return "redirect:/";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Sorry, voting is not available.")
    @ExceptionHandler({ElectionsStateException.class})
    public void badVoteRequest() {
        LOGGER.warn("Bad voter form request. Elections are " + stateService.getState());
    }

}
