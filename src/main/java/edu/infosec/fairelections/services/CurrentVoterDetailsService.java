package edu.infosec.fairelections.services;

import edu.infosec.fairelections.model.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentVoterDetailsService implements UserDetailsService {
    private final VoterService voterService;

    @Autowired
    public CurrentVoterDetailsService(VoterService voterService) {
        this.voterService = voterService;
    }

    @Override
    public CurrentVoter loadUserByUsername(String email) throws UsernameNotFoundException {
        Voter voter = voterService.getVoterByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
        return new CurrentVoter(voter);
    }
}
