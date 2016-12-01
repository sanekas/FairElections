package edu.infosec.fairelections.services.impl;

import edu.infosec.fairelections.model.entities.impl.CurrentUser;
import edu.infosec.fairelections.model.api.UserRole;
import edu.infosec.fairelections.services.api.CurrentUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == UserRole.ADMIN || currentUser.getId().equals(userId));
    }

}
