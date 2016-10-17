package edu.infosec.fairelections.services;

import edu.infosec.fairelections.model.Role;
import edu.infosec.fairelections.model.Voter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.concurrent.atomic.AtomicLong;

public class CurrentVoter extends User {
    private final Voter voter;

    public CurrentVoter(Voter voter) {
        super(voter.getUsername(),
                voter.getPasswordHash(),
                AuthorityUtils.createAuthorityList(voter.getRole().toString()));
        this.voter = voter;
    }

    public Voter getVoter() {
        return voter;
    }

    public AtomicLong getId() {
        return voter.getId();
    }

    public Role getRole() {
        return voter.getRole();
    }
}
