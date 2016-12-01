package edu.infosec.fairelections.model.api;

import edu.infosec.fairelections.model.entities.impl.Voter;

public interface VoterFacory {
    Voter createVoter(Long voterId, Vote vote, Long twinVoterId);
}
