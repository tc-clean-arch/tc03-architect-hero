package com.fiap.core.usecases.auth;

public interface PasswordEncoder {
    String hash(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
