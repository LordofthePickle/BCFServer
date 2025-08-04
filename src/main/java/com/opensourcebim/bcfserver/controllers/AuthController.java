package com.opensourcebim.bcfserver.controllers;

import com.opensourcebim.bcfserver.dtos.ForgotPasswordDTO;
import com.opensourcebim.bcfserver.dtos.LoginRequestDTO;
import com.opensourcebim.bcfserver.dtos.PasswordResetDTO;
import com.opensourcebim.bcfserver.dtos.RegisterRequestDTO;
import com.opensourcebim.bcfserver.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request){
        authService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authService.loginUser(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        authService.logoutUser();
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDTO request){
        authService.forgotPassword(request);
        return ResponseEntity.ok("Password reset email sent successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetDTO request){
        authService.resetPassword(request);
        return ResponseEntity.ok("Password reset successfully");
    }

}
