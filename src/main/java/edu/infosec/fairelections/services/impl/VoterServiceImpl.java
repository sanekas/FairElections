package edu.infosec.fairelections.services.impl;

import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.model.entities.Voter;
import edu.infosec.fairelections.model.entities.VoterForm;
import edu.infosec.fairelections.repository.VoterRepository;
import edu.infosec.fairelections.services.api.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;

    private long lastAddedId = -1; //TODO: Change type to AtomicLong
    private long firstAddedId = 0; //TODO: Change type to AtomicLong

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
        return voterRepository.findAll(new Sort("id"));
    }

    @Override
    public Voter save(Long voterId, VoterForm voterForm) {
        Optional<Voter> voterWrap = voterRepository.findOneById(voterId);
        Voter voter;
        if (voterWrap.isPresent()) {
            voter = voterWrap.get();
        } else {
            voter = new Voter();
            if (lastAddedId != -1) {
                voterRepository.getOne(firstAddedId).setTwinVoterId(voterId);
            } else {
                firstAddedId = voterId;
            }
            lastAddedId = voterId;
        }
        updateVoter(voter, voterId, voterForm);
        return voterRepository.save(voter);
    }

    private void updateVoter(Voter voter, Long voterId, VoterForm voterForm) {
        voter.setId(voterId);
        voter.setTwinVoterId(lastAddedId);
        voter.setVote(Vote.valueOf(voterForm.getVote()));
    }
}
