package com.opensourcebim.bcfserver.controllers;

import com.opensourcebim.bcfserver.dtos.PasswordChangeDTO;
import com.opensourcebim.bcfserver.dtos.UserDTO;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.services.AuthService;
import com.opensourcebim.bcfserver.services.AuthServiceImpl;
import com.opensourcebim.bcfserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AccountController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    //Getters

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(UserDTO.from(user));
    }

    /* TODO: could add direct methods for getting user information (though it's not necessary) */

    //Setters

    @PutMapping("/me/updateUsername")
    public ResponseEntity<?> updateUsername(String newUsername) {
        userService.updateUsernameForLoggedInUser(newUsername);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/me/updateEmail")
    public ResponseEntity<?> updateEmail(String newEmail) {
        userService.updateEmailForLoggedInUser(newEmail);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/me/updatePassword")
    public ResponseEntity<?> updatePassword(PasswordChangeDTO request) {
        userService.updatePasswordForLoggedInUser(request);
        return ResponseEntity.ok().build();
    }




}
