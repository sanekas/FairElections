package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.services.api.ElectionsStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ElectionsController {
    private final ElectionsStateService electionsStateService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public ElectionsController(ElectionsStateService electionsStateService) {
        this.electionsStateService = electionsStateService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/updateElectionsState", method = RequestMethod.POST)
    public String updateElectionsState() {
        electionsStateService.updateElectionsState();
        return "redirect:/";
    }

}
