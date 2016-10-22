package edu.infosec.fairelections.model.entities;

import edu.infosec.fairelections.model.api.UserRole;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private final User user;

    public CurrentUser(User user) {
        super(user.getUsername(),
                user.getPasswordHash(),
                AuthorityUtils.createAuthorityList(user.getUserRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public UserRole getRole() {
        return user.getUserRole();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                '}';
    }
}
