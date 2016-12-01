package edu.infosec.fairelections.services.api;

import edu.infosec.fairelections.model.entities.impl.CurrentUser;

public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
