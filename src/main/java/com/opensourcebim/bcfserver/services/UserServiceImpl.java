package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.PasswordChangeDTO;
import com.opensourcebim.bcfserver.dtos.ProjectDTO;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.UserType;
import com.opensourcebim.bcfserver.repositories.UserRepository;
import com.opensourcebim.bcfserver.utils.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    //GETTERS

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(STR."User, \{username} not found")));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> getUserByUoid(Long uoid){
        return userRepository.findByUoid(uoid);
    }

    @Override
    public List<User> getUsersByType(UserType userType){
        return userRepository.findByUserType(userType);
    }

    public Page<User> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public List<ProjectDTO> getAllProjectsForCurrentUser (){
        User user = authService.getCurrentUser();
        return user.getAccessibleProjects().stream().map(ProjectDTO::from).toList();
    }

    //MODIFIERS

    @Override
    public void updateUsernameForLoggedInUser(String username) {
        if (!ValidationUtils.isValidUsername(username)){
            throw new IllegalArgumentException("Invalid username");
        }
        User user = authService.getCurrentUser();
        user.setUsername(username);
        userRepository.save(user);
    }

    @Override
    public void updateEmailForLoggedInUser(String email) {
        if (!ValidationUtils.isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email");
        }
        User user = authService.getCurrentUser();
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void updatePasswordForLoggedInUser(PasswordChangeDTO request) {
        String currentUserPassword = authService.getCurrentUser().getPassword();
        if (!passwordEncoder.matches(request.getOldPassword(), currentUserPassword)){
            throw new IllegalArgumentException("Incorrect Old Password");
        }
        if (!ValidationUtils.isStrongPassword(request.getNewPassword())){
            throw new IllegalArgumentException("Invalid password");
        }
        User user = authService.getCurrentUser();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateEmailForUser(Long uoid, String email) {
        User user = getUserByUoid(uoid).get();
        if (!ValidationUtils.isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email");
        }
        user.setEmail(email);
        userRepository.save(user);
    }
}
