package edu.infosec.fairelections.controllers;

import edu.infosec.fairelections.model.entity.api.VoterCreateForm;
import edu.infosec.fairelections.model.service.api.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VoterCreateFormValidator implements Validator {
    private final VoterService userService;

    @Autowired
    public VoterCreateFormValidator(VoterService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(VoterCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        VoterCreateForm form = (VoterCreateForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
    }

    private void validatePasswords(Errors errors, VoterCreateForm form) {
        if (!form.getPassword().equals(form.getRepeatedPassword())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateEmail(Errors errors, VoterCreateForm form) {
        if (userService.getVoterByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }
}
