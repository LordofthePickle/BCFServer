package com.opensourcebim.bcfserver.controllers;

import com.opensourcebim.bcfserver.dtos.user.EmailDTO;
import com.opensourcebim.bcfserver.dtos.user.UpdatePasswordDTO;
import com.opensourcebim.bcfserver.dtos.user.UsernameDTO;
import com.opensourcebim.bcfserver.dtos.user.UserDTO;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.services.AuthService;
import com.opensourcebim.bcfserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> updateUsername(@RequestBody UsernameDTO request) {
        userService.updateUsernameForLoggedInUser(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/me/updateEmail")
    public ResponseEntity<?> updateEmail(@RequestBody EmailDTO request) {
        userService.updateEmailForLoggedInUser(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/me/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDTO request) {
        userService.updatePasswordForLoggedInUser(request);
        return ResponseEntity.ok().build();
    }




}
