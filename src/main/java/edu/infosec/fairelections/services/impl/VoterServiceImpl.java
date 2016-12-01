package edu.infosec.fairelections.services.impl;

import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.model.api.VoterFacory;
import edu.infosec.fairelections.model.entities.impl.Voter;
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
    private final VoterFacory voterFacory;

    private AtomicLong lastAddedId = new AtomicLong(-1);
    private AtomicLong firstAddedId = new AtomicLong(0);

    @Autowired
    public VoterServiceImpl(VoterRepository voterRepository, VoterFacory voterFacory) {
        this.voterRepository = voterRepository;
        this.voterFacory = voterFacory;
    }

    @Override
    public Optional<Voter> getVoterById(Long id) {
        return voterRepository.findOneById(id);
    }

    @Override
    public Collection<Voter> getAllVoters() {
        return voterRepository.findAll(new Sort("id"));
    }


    /**
     * Algorithm description:
     *
     * if voter exists in PBB we will set only his new vote
     * if voter doesn't exist in PBB we will create new voter will set all his data
     * if PBB is not empty (without the current voter) we will put in the first voter's
     * twin_voter_id the id of the current voter
     * if PBB is empty (without the current voter) the current voter will be the first thus  firstAddedId = voterId
     * mark that the current voter is last
     *
     * @param voterId
     * @param vote
     * @return new voter
     */
    @Override
    public Voter save(Long voterId, Vote vote) {
        Optional<Voter> voterWrap = voterRepository.findOneById(voterId);
        Voter voter;
        if (voterWrap.isPresent()) {
            voter = voterWrap.get();
            voter.setVote(vote);
        } else {
            voter = voterFacory.createVoter(voterId, vote, lastAddedId.get());
            if (lastAddedId.get() != -1L) {
                voterRepository.getOne(firstAddedId.get()).setTwinVoterId(voterId);
            } else {
                firstAddedId.set(voterId);
            }
            lastAddedId.set(voterId);
        }
        return voterRepository.save(voter);
    }
}
