package com.opensourcebim.bcfserver.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateToken(String username);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
