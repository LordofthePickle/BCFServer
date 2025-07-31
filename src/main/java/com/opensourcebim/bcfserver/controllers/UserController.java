package com.opensourcebim.bcfserver.controllers;

import com.opensourcebim.bcfserver.dtos.UserDTO;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @PutMapping("/email")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUserEmail(@RequestBody String newEmail, @RequestBody UserDTO user) {
        userService.updateEmailForUser(newEmail);
        return ResponseEntity.ok().build();
    }
}
