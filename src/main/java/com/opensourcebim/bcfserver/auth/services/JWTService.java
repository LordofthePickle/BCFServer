package com.opensourcebim.bcfserver.auth.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;


public interface JWTService {
    String generateToken(UserDetails userDetails);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    long getExpirationMillis(String token);
    String extractToken(Authentication authentication);
}
