package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.model.entities.VoterForm;
import edu.infosec.fairelections.services.api.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;


@Controller
public class VoteController {
    private final VoterService voterService;
    private final Validator voterCreateFormValidator;


    @Autowired
    public VoteController(VoterService voterService,
                          Validator voterCreateFormValidator) {
        this.voterService = voterService;
        this.voterCreateFormValidator = voterCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(voterCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public ModelAndView getVoterPage(@PathVariable Long id) {
        return new ModelAndView("vote", "form", new VoterForm());
    }

    @PreAuthorize("hasAuthority('VOTER') && @currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public String handleVoterCreateForm(@PathVariable Long id,
                                        @Valid @ModelAttribute("form") VoterForm voterForm,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user";
        }
        try {
            voterService.save(voterForm);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("voter.id");
            return "user";
        }
        return "user";
    }

}
