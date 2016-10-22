package edu.infosec.fairelections.utils.impl;

import edu.infosec.fairelections.utils.api.EncryptionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptEncryptionService implements EncryptionService {

    private final PasswordEncoder passwordEncoder;

    public BCryptEncryptionService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public PasswordEncoder getEncoder() {
        return passwordEncoder;
    }
}
