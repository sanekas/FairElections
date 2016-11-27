package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.controllers.exceptions.NoSelectionsResultsException;
import edu.infosec.fairelections.services.api.SelectionsState;
import edu.infosec.fairelections.services.api.VoterService;
import edu.infosec.fairelections.services.impl.SelectionsStateService;
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
    private final SelectionsStateService stateService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public ResultsController(VoterService voterService, SelectionsStateService stateService) {
        this.voterService = voterService;
        this.stateService = stateService;
    }

    @RequestMapping("/results")
    public ModelAndView getUsersPage() {
        SelectionsState selectionsState = stateService.getState();
        switch (selectionsState) {
            case NOT_STARTED:
            case RUNNING:
                throw new NoSelectionsResultsException();
            case ENDED:
                return new ModelAndView("results", "voters", voterService.getAllVoters());
        }
        return new ModelAndView("results", "voters", voterService.getAllVoters());
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Selections results are still not available.")
    @ExceptionHandler({NoSelectionsResultsException.class})
    public void badResultsRequest() {
        LOGGER.warn("Bad results request. Selections are " + stateService.getState());
    }
}