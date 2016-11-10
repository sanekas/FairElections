package edu.infosec.fairelections.services.api;

import edu.infosec.fairelections.model.entities.Voter;
import edu.infosec.fairelections.model.entities.VoterForm;

import java.util.Collection;
import java.util.Optional;

public interface VoterService {
    Optional<Voter> getVoterById(Long id);

    Collection<Voter> getAllVoters();

    Voter save(Long voterId, VoterForm form);
}
