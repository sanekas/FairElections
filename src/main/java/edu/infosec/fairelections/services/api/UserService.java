package edu.infosec.fairelections.services.api;

import edu.infosec.fairelections.model.entities.User;
import edu.infosec.fairelections.model.entities.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}
