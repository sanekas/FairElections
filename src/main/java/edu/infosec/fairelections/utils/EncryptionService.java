package edu.infosec.fairelections.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public enum  EncryptionService {

    BCRYPT(new BCryptPasswordEncoder());

    private final PasswordEncoder encoder;

    EncryptionService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public PasswordEncoder getEncoder() {
        return encoder;
    }
}
