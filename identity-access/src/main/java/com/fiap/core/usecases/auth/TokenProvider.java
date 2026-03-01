package com.fiap.core.usecases.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

import java.util.UUID;


public interface TokenProvider {

    String generateToken(UUID userId, String email, String papel);

    Jws<Claims> parseToken(String token) throws JwtException;
}

