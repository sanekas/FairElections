package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.services.api.ElectionsState;
import edu.infosec.fairelections.services.api.ElectionsStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final ElectionsStateService electionsStateService;


    @Autowired
    public HomeController(ElectionsStateService electionsStateService) {
        this.electionsStateService = electionsStateService;
    }

    @RequestMapping("/")
    public ModelAndView getHomePage() {
        ModelAndView homeModelAndView = new ModelAndView("home");
        ElectionsState electionsState = electionsStateService.getState();
        homeModelAndView.addObject("electionsButton", electionsState.getButtonTitle());
        homeModelAndView.addObject("electionsState", electionsState.getStateMessage());
        homeModelAndView.addObject("votingOpened", electionsState.isVotingOpened());
        return homeModelAndView;
    }

}