package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.controllers.exceptions.ElectionsStateException;
import edu.infosec.fairelections.services.api.ElectionsState;
import edu.infosec.fairelections.services.api.ElectionsStateService;
import edu.infosec.fairelections.services.api.VoterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResultsController {
    private final VoterService voterService;
    private final ElectionsStateService stateService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public ResultsController(VoterService voterService, ElectionsStateService stateService) {
        this.voterService = voterService;
        this.stateService = stateService;
    }

    @RequestMapping("/results")
    public ModelAndView getUsersPage() {
        ElectionsState electionsState = stateService.getState();
        if (electionsState != ElectionsState.ENDED) {
            throw new ElectionsStateException();
        }
        return new ModelAndView("results", "voters", voterService.getAllVoters());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Elections results are still not available.")
    @ExceptionHandler({ElectionsStateException.class})
    public void badResultsRequest() {
        LOGGER.warn("Bad results request. Elections are " + stateService.getState());
    }
}