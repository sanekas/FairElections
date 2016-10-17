package edu.infosec.fairelections.services;

import edu.infosec.fairelections.model.VoterCreateForm;
import edu.infosec.fairelections.model.Voter;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public interface VoterService {
    Optional<Voter> getVoterById(AtomicLong id);

    Optional<Voter> getVoterByEmail(String email);

    Collection<Voter> getAllVoters();

    Voter create(VoterCreateForm form);
}
