package edu.infosec.fairelections.model.service.api;

import edu.infosec.fairelections.model.entity.api.VoterCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VoterCreateFormValidator implements Validator {
    private final VoterService voterService;

    @Autowired
    public VoterCreateFormValidator(VoterService userService) {
        this.voterService = userService;
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
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateEmail(Errors errors, VoterCreateForm form) {
        if (voterService.getVoterByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "Voter with this email already exists");
        }
    }
}
