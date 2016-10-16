package edu.infosec.fairelections.model.service.impl;

import edu.infosec.fairelections.model.entity.api.VoterCreateForm;
import edu.infosec.fairelections.model.entity.impl.Voter;
import edu.infosec.fairelections.model.service.api.VoterRepository;
import edu.infosec.fairelections.model.service.api.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;

    @Autowired
    public VoterServiceImpl(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Override
    public Optional<Voter> getVoterById(AtomicLong id) {
        return Optional.ofNullable(voterRepository.findOne(id));
    }

    @Override
    public Optional<Voter> getVoterByEmail(String email) {
        return voterRepository.findOneByEmail(email);
    }

    @Override
    public Collection<Voter> getAllVoters() {
        return voterRepository.findAll(new Sort("email"));
    }

    @Override
    public Voter create(VoterCreateForm form) {
        Voter voter = new Voter();
        voter.setEmail(form.getEmail());
        voter.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        return null;
    }
}
