package com.opensourcebim.bcfserver.controllers;

import com.opensourcebim.bcfserver.dtos.CreationTimeDTO;
import com.opensourcebim.bcfserver.dtos.CreationTimeRangeDTO;
import com.opensourcebim.bcfserver.dtos.PageRequestDTO;
import com.opensourcebim.bcfserver.dtos.user.*;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.UserType;
import com.opensourcebim.bcfserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Getters
    @GetMapping("/by_uoid")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserById(@ModelAttribute UoidDTO request) {
        return ResponseEntity.ok(userService.getUserByUoid(request));
    }

    @GetMapping("/by_username")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserByUsername(@ModelAttribute UsernameDTO request) {
        return ResponseEntity.ok(userService.getUserByUsername(request));
    }

    @GetMapping("/by_email")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserByEmail(@ModelAttribute EmailDTO request) {
        return ResponseEntity.ok(userService.getUserByEmail(request));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getAllUsers(@ModelAttribute PageRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by("username").ascending()
                        .and(Sort.by("userType").ascending()));
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/all/by_userType")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getUsersByType(@ModelAttribute UserTypeDTO request,
                                                     @ModelAttribute PageRequestDTO pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize());
        return ResponseEntity.ok(userService.getUsersByType(request, pageable));
    }

    @GetMapping("/all/by_creationTime")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getAllUsersByCreationTime(@ModelAttribute PageRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("creationTime").descending());
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/all/by_creationTime_After")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getUsersByCreationTimeAfter(@ModelAttribute CreationTimeDTO request,
                                                                  @ModelAttribute PageRequestDTO pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), Sort.by("creationTime").descending());
        return ResponseEntity.ok(userService.getUsersByCreationTimeAfter(request, pageable));
    }

    @GetMapping("/all/by_creationTime_Before")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getUsersByCreationTimeBefore(@ModelAttribute CreationTimeDTO request,
                                                                   @ModelAttribute PageRequestDTO pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), Sort.by("creationTime").descending());
        return ResponseEntity.ok(userService.getUsersByCreationTimeBefore(request, pageable));
    }

    @GetMapping("/all/by_creationTime_Range")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getUsersByCreationTimeRange(@ModelAttribute CreationTimeRangeDTO request,
                                                                     @ModelAttribute PageRequestDTO pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), Sort.by("creationTime").descending());
        return ResponseEntity.ok(userService.getUsersByCreationTimeRange(request, pageable));
    }

    //Putters

    @PutMapping("/{uoid}/email")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUserEmail(@PathVariable Long uoid, @RequestBody String email) {
        userService.updateEmailForUser(uoid, email);
        return ResponseEntity.ok().build();
    }
}
