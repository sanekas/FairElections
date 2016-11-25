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
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;

    private AtomicLong lastAddedId = new AtomicLong(-1);
    private AtomicLong firstAddedId = new AtomicLong(0);

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
            //if voter exists in PBB we will set only his new vote
            voter = voterWrap.get();
            voter.setVote(Vote.valueOf(voterForm.getVote()));
        } else {
            //if voter doesn't exist in PBB we will create new voter will set all his data
            voter = new Voter();
            addVoterToPBB(voter, voterId, voterForm);
            if (lastAddedId.get() != -1L) {
                //if PBB is not empty (without the current voter) we will put in the first voter's twin_voter_id the id of the current voter
                voterRepository.getOne(firstAddedId.get()).setTwinVoterId(voterId);
            } else {
                //if PBB is empty (without the current voter) the current voter will be the first thus  firstAddedId = voterId
                firstAddedId.set(voterId);
            }
            lastAddedId.set(voterId); // mark that the current voter is last
        }
        return voterRepository.save(voter);
    }

    private void addVoterToPBB(Voter voter, Long voterId, VoterForm voterForm) {
        voter.setId(voterId);
        voter.setTwinVoterId(lastAddedId.get());
        voter.setVote(Vote.valueOf(voterForm.getVote()));
    }
}
