package com.fiap.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

public interface TokenProvider {

    Jws<Claims> parseToken(String token) throws JwtException;
}