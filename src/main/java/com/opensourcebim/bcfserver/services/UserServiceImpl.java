package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.EmailUpdateForUserRequestDTO;
import com.opensourcebim.bcfserver.dtos.ProjectDTO;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.UserType;
import com.opensourcebim.bcfserver.repositories.UserRepository;
import com.opensourcebim.bcfserver.utils.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //GETTERS

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(STR."User not found: \{username}"));
    }

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
    public List<User> getUsersByType(UserType userType){
        return userRepository.findByUserType(userType);
    }

    public Page<User> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public List<ProjectDTO> getAllProjectsForCurrentUser (){
        User user = getCurrentUser();
        return user.getAccessibleProjects().stream().map(ProjectDTO::from).toList();
    }

    //MODIFIERS

    @Override
    public void updateEmailForLoggedInUser(String email) {
        if (!ValidationUtils.isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email");
        }
        User user = getCurrentUser();
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void updateEmailForUser(EmailUpdateForUserRequestDTO request) {
        User user = getUserByUsername(request.getUsername()).get();
        String email = request.getEmail();
        if (!ValidationUtils.isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email");
        }
        user.setEmail(email);
        userRepository.save(user);
    }
}
