package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.services.api.ElectionsState;
import edu.infosec.fairelections.services.impl.ElectionsStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ElectionsController {
    private final ElectionsStateService electionsState;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public ElectionsController(ElectionsStateService electionsState) {
        this.electionsState = electionsState;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/updateElectionsState", method = RequestMethod.POST)
    public String updateElectionsState() {
        switch (electionsState.getState()) {
            case NOT_STARTED:
                electionsState.setState(ElectionsState.RUNNING);
                LOGGER.info("Elections started!");
                break;
            case RUNNING:
                electionsState.setState(ElectionsState.ENDED);
                LOGGER.info("Elections ended!");
                break;
            default:
                LOGGER.info("Incorrect elections state update query.");
        }
        return "redirect:/";
    }

}
