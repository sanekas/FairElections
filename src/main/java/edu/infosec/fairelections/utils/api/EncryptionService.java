package edu.infosec.fairelections.utils.api;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface EncryptionService {
    PasswordEncoder getEncoder();
}
