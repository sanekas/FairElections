package edu.infosec.fairelections.services.api;

import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.model.entities.impl.Voter;

import java.util.Collection;
import java.util.Optional;

public interface VoterService {
    Optional<Voter> getVoterById(Long id);

    Collection<Voter> getAllVoters();

    Voter save(Long voterId, Vote vote);
}
