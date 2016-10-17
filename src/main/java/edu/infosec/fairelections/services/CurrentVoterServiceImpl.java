package edu.infosec.fairelections.services;

import edu.infosec.fairelections.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class CurrentVoterServiceImpl implements CurrentVoterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentVoterDetailsService.class);

    @Override
    public boolean canAccessVoter(CurrentVoter currentUser, AtomicLong userId) {
        LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }

}
