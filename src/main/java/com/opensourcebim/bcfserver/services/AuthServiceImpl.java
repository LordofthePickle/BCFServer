package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.auth.services.CustomUserDetailsService;
import com.opensourcebim.bcfserver.auth.services.JWTService;
import com.opensourcebim.bcfserver.auth.services.RedisTokenBlacklistService;
import com.opensourcebim.bcfserver.dtos.auth.ForgotPasswordDTO;
import com.opensourcebim.bcfserver.dtos.auth.LoginRequestDTO;
import com.opensourcebim.bcfserver.dtos.auth.PasswordResetDTO;
import com.opensourcebim.bcfserver.dtos.auth.RegisterRequestDTO;
import com.opensourcebim.bcfserver.exceptions.AuthException;
import com.opensourcebim.bcfserver.exceptions.EmailNotFoundException;
import com.opensourcebim.bcfserver.exceptions.TokenExpiredException;
import com.opensourcebim.bcfserver.models.PasswordResetToken;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.repositories.UserRepository;
import com.opensourcebim.bcfserver.repositories.resetTokenRepository;
import com.opensourcebim.bcfserver.utils.ValidationUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;
    private final RedisTokenBlacklistService redisTokenBlacklistService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final resetTokenRepository resetTokenRepository;
    private final EmailService emailService;

    public AuthServiceImpl(UserRepository userRepository, UserService userService, CustomUserDetailsService userDetailsService, RedisTokenBlacklistService redisTokenBlacklistService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService, resetTokenRepository resetTokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.redisTokenBlacklistService = redisTokenBlacklistService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.resetTokenRepository = resetTokenRepository;
        this.emailService = emailService;
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
            return jwtService.generateToken(userDetailsService.loadUserByUsername(request.getUsername()));
        }
        catch (Exception e) {
            throw new AuthException("Invalid credentials");
        }
    }

    @Override
    public void logoutUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String token = jwtService.extractToken(auth);

        if (auth != null && auth.getAuthorities() != null) {
            boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                long expirationMillis = jwtService.getExpirationMillis(token);
                redisTokenBlacklistService.blacklistToken(token, expirationMillis);
            }
        }
    }

    @Override
    public void forgotPassword(ForgotPasswordDTO request) {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(STR."User not found: \{email}"));

        PasswordResetToken resetToken = new PasswordResetToken(user);
        resetTokenRepository.save(resetToken);

        emailService.sendResetEmail(user.getEmail(), resetToken.getToken());
    }

    @Override
    public void resetPassword(PasswordResetDTO request) {
        PasswordResetToken token = resetTokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new AuthException("Invalid token"));
        if (token.isExpired()) {
            throw new TokenExpiredException("Token expired");
        }

        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        resetTokenRepository.delete(token);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(STR."User not found: \{username}"));
    }
}
