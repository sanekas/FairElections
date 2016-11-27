package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.services.impl.ElectionsStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final ElectionsStateService electionsState;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public HomeController(ElectionsStateService electionsState) {
        this.electionsState = electionsState;
    }

    @RequestMapping("/")
    public ModelAndView getHomePage() {
        ModelAndView homeModelAndView = new ModelAndView();

        String electionsButtonTitle = null;
        String electionsStateMessage = null;
        switch (electionsState.getState()) {
            case NOT_STARTED:
                electionsButtonTitle = "Start elections";
                electionsStateMessage = "Elections are about to begin.";
                break;
            case RUNNING:
                electionsButtonTitle = "End elections";
                electionsStateMessage = "Elections are running.";
                break;
            case ENDED:
                electionsStateMessage = "Elections are over.";
                break;
        }
        homeModelAndView.addObject("electionsButton", electionsButtonTitle);
        homeModelAndView.addObject("electionsState", electionsStateMessage);
        homeModelAndView.setViewName("home");
        return homeModelAndView;
    }

}