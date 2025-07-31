package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.auth.JWTService;
import com.opensourcebim.bcfserver.dtos.LoginRequestDTO;
import com.opensourcebim.bcfserver.dtos.RegisterRequestDTO;
import com.opensourcebim.bcfserver.exceptions.AuthException;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.repositories.UserRepository;
import com.opensourcebim.bcfserver.utils.ValidationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthServiceImpl(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public User registerUser(RegisterRequestDTO request) {
        if (!ValidationUtils.isValidEmail(request.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (!ValidationUtils.isValidUsername(request.getUsername())) {
            throw new IllegalArgumentException("Invalid username");
        }

        if (userService.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        if (!ValidationUtils.isStrongPassword((String) request.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 8 characters long, include uppercase, lowercase, and digits.");
        }
        User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getUserType());
        return userRepository.save(user);
    }

    @Override
    public String loginUser(LoginRequestDTO request) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
            );
            authenticationManager.authenticate(authentication);
            return jwtService.generateToken(request.getUsername());
        }
        catch (Exception e) {
            throw new AuthException("Invalid credentials");
        }
    }
}
