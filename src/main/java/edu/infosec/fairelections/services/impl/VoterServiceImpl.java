package edu.infosec.fairelections.services.impl;

import edu.infosec.fairelections.model.entities.UserCreateForm;
import edu.infosec.fairelections.model.entities.Voter;
import edu.infosec.fairelections.repository.VoterRepository;
import edu.infosec.fairelections.services.api.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;

    @Autowired
    public VoterServiceImpl(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Override
    public Optional<Voter> getVoterById(Long id) {
        return voterRepository.findOneById(id);
    }

    @Override
    public Collection<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    @Override
    public Voter create(UserCreateForm form) {
        return null;
    }
}
