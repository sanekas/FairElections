package edu.infosec.fairelections.services.impl;

import edu.infosec.fairelections.model.api.Vote;
import edu.infosec.fairelections.services.api.CandidatesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CandidatesServiceImpl implements CandidatesService {
    private final List<String> candidates;

    public CandidatesServiceImpl() {
        candidates = new ArrayList<>();
        Arrays.asList(Vote.values()).forEach((Vote vote) -> candidates.add(vote.name()));
    }

    @Override
    public List<String> getCandidates() {
        return new ArrayList<>(candidates);
    }
}
