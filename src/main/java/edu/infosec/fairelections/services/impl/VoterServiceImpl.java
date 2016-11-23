package edu.infosec.fairelections.services.impl;

import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.model.entities.Voter;
import edu.infosec.fairelections.model.entities.VoterForm;
import edu.infosec.fairelections.repository.VoterRepository;
import edu.infosec.fairelections.services.api.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private long lastAddedId = -1;
    private long firstAddedId = 0;

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
            voter.setId(voterId);
            voter.setTwinVoterId(lastAddedId);
            voter.setVote(Vote.valueOf(voterForm.getVote()));
            if (lastAddedId != -1) {
                updateFirstUser(voterId, firstAddedId);
            } else {
                firstAddedId = voterId;
            }
            lastAddedId = voterId;
        }
        return voterRepository.save(voter);
    }

    private void updateFirstUser(Long lastId, Long firstId) {
        final String query = new String("UPDATE voter SET twin_voter_id = ? WHERE id = ?");
        jdbcTemplate.update(query, lastId, firstId);
    }
}
