package edu.infosec.fairelections.services.api;

import edu.infosec.fairelections.model.entities.UserCreateForm;
import edu.infosec.fairelections.model.entities.Voter;

import java.util.Collection;
import java.util.Optional;

public interface VoterService {
    Optional<Voter> getVoterById(Long id);

    Collection<Voter> getAllVoters();

    Voter create(UserCreateForm form);
}
