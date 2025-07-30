package com.opensourcebim.bcfserver.services;

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
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
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

    //CREATION

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    //MODIFIERS

    @Override
    public void updateEmailForLoggedInUser(String email) {
        if (!ValidationUtils.isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(STR."User not found: \{username}"));
        user.setEmail(email);
        userRepository.save(user);
    }
}
