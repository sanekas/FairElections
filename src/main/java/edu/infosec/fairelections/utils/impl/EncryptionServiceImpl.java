package edu.infosec.fairelections.utils.impl;

import edu.infosec.fairelections.utils.api.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public EncryptionServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String data) {
        return passwordEncoder.encode(data);
    }
}
