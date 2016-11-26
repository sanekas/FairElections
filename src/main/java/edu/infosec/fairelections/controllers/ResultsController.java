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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResultsController {
    private final VoterService voterService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private WebApplicationContext context;

    @Autowired
    public ResultsController(VoterService voterService) {
        this.voterService = voterService;
    }

    @RequestMapping("/results")
    public ModelAndView getUsersPage() {
        SelectionsState selectionsState = getSelectionsState();
        switch (selectionsState) {
            case NOT_STARTED:
            case RUNNING:
                throw new NoSelectionsResultsException();
            case ENDED:
                return new ModelAndView("results", "voters", voterService.getAllVoters());
        }
        return new ModelAndView("results", "voters", voterService.getAllVoters());
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="No selections results available.")
    @ExceptionHandler({NoSelectionsResultsException.class})
    public void badResultsRequest() {
        LOGGER.warn("Bad results request.");
    }

    private SelectionsState getSelectionsState() {
        SelectionsStateService service = (SelectionsStateService) context.getBean("selectionsStateService");
        return service.getState();
    }
}