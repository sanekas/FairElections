package edu.infosec.fairelections.model.api;

import edu.infosec.fairelections.model.entities.impl.User;

public interface UserFactory {
    User createUser(String userName, String passwordHash, UserRole userRole);
}
