package com.fiap.infrastructure.security;

import com.fiap.core.usecases.auth.PasswordEncoder;
import jakarta.enterprise.context.ApplicationScoped;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String hash(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));

    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2")) {
            return false;
        }
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}
