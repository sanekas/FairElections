package edu.infosec.fairelections.model.entities.factories;

import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.model.api.VoterFacory;
import edu.infosec.fairelections.model.entities.impl.Voter;
import org.springframework.stereotype.Service;

@Service
public class VoterFactoryImpl implements VoterFacory {
    @Override
    public Voter createVoter(Long voterId, Vote vote, Long twinVoterId) {
        Voter voter = new Voter();
        voter.setId(voterId);
        voter.setVote(vote);
        voter.setTwinVoterId(twinVoterId);
        return voter;
    }
}
