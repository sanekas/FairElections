package edu.infosec.fairelections.model.service.api;

import edu.infosec.fairelections.model.entity.api.VoterCreateForm;
import edu.infosec.fairelections.model.entity.impl.Voter;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public interface VoterService {
    Optional<Voter> getVoterById(AtomicLong id);

    Optional<Voter> getVoterByEmail(String email);

    Collection<Voter> getAllVoters();

    Voter create(VoterCreateForm form);
}
