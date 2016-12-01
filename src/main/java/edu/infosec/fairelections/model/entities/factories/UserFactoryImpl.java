package edu.infosec.fairelections.model.entities.factories;

import edu.infosec.fairelections.model.api.UserFactory;
import edu.infosec.fairelections.model.api.UserRole;
import edu.infosec.fairelections.model.entities.impl.User;
import org.springframework.stereotype.Service;

@Service
public class UserFactoryImpl implements UserFactory {
    @Override
    public User createUser(String userName, String passwordHash, UserRole userRole) {
        User user = new User();
        user.setUsername(userName);
        user.setPasswordHash(passwordHash);
        user.setUserRole(userRole);
        return user;
    }
}
