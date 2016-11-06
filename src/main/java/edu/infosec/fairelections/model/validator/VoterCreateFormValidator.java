package edu.infosec.fairelections.model.validator;

import edu.infosec.fairelections.model.entities.VoterForm;
import edu.infosec.fairelections.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class VoterCreateFormValidator implements Validator {
    private final UserService userService;

    @Autowired
    public VoterCreateFormValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(VoterForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        VoterForm voterForm = (VoterForm) o;
        validateId(errors, voterForm);
        validateVote(errors, voterForm);
    }

    private void validateId(Errors errors, VoterForm voterForm) {
        if (!userService.getUserById(voterForm.getId()).isPresent()) {
            errors.reject("user.id", "User with such id is not found!");
        }
    }

    private void validateVote(Errors errors, VoterForm voterForm) {
        if (voterForm.getVote() == null) {
            errors.reject("vote.null", "Vote is not found!");
        }
    }
}
