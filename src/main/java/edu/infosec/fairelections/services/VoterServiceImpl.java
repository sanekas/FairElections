package edu.infosec.fairelections.services;

import edu.infosec.fairelections.model.Voter;
import edu.infosec.fairelections.model.VoterCreateForm;
import edu.infosec.fairelections.repository.VoterRepository;
import edu.infosec.fairelections.utils.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
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
        voter.setPasswordHash(EncryptionService.BCRYPT.getEncoder().encode(form.getPassword()));
        voter.setRole(form.getRole());
        return voter;
    }
}
