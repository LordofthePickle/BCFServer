package com.opensourcebim.bcfserver.controllers;

import com.opensourcebim.bcfserver.dtos.UserDTO;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(new UserDTO(user));
    }

    @GetMapping("/email")
    public ResponseEntity<?> updateEmail(String newEmail) {
        userService.updateEmailForLoggedInUser(newEmail);
        return ResponseEntity.ok().build();
    }
}
