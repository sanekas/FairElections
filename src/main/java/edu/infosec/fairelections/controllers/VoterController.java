package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.model.entity.api.VoterCreateForm;
import edu.infosec.fairelections.model.service.api.VoterCreateFormValidator;
import edu.infosec.fairelections.model.service.api.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class VoterController {

    private final VoterService voterService;
    private final VoterCreateFormValidator voterCreateFormValidator;

    @Autowired
    public VoterController(VoterService voterService, VoterCreateFormValidator voterCreateFormValidator) {
        this.voterService = voterService;
        this.voterCreateFormValidator = voterCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(voterCreateFormValidator);
    }

    @RequestMapping("/vote/{id}")
    public ModelAndView getVoterPage(@PathVariable AtomicLong id) {
        return new ModelAndView("voter", "voter", voterService.getVoterById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Voter=%s not found", id))));
    }

    @RequestMapping(value = "/voter/create", method = RequestMethod.GET)
    public ModelAndView getVoterCreatePage() {
        return new ModelAndView("voter_create", "form", new VoterCreateForm());
    }

    @RequestMapping(value = "/voter/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") VoterCreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "voter_create";
        }
        try {
            voterService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "voter_create";
        }
        return "redirect:/voters";
    }

}