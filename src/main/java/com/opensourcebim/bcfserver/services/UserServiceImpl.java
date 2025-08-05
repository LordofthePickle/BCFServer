package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.CreationTimeDTO;
import com.opensourcebim.bcfserver.dtos.user.*;
import com.opensourcebim.bcfserver.dtos.project.ProjectDTO;
import com.opensourcebim.bcfserver.exceptions.EmailNotFoundException;
import com.opensourcebim.bcfserver.exceptions.IdNotFoundException;
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
    public UserDTO getUserByUsername(UsernameDTO request) {
        String username = request.getUsername();
        return UserDTO.from(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(STR."User, \{username}, not found")));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDTO getUserByEmail(EmailDTO request) {
        String email = request.getEmail();
        return UserDTO.from(userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(STR."User with email: \{email} not found")));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDTO getUserByUoid(UoidDTO request){
        Long uoid = request.getUoid();
        return UserDTO.from(userRepository.findByUoid(uoid)
                .orElseThrow(() -> new IdNotFoundException(STR."User with uoid: \{uoid} not found")));

    }

    @Override
    public User getUserByUoid(Long uoid){
        return userRepository.findByUoid(uoid)
                .orElseThrow(() -> new IdNotFoundException(STR."User with uoid: \{uoid} not found"));
    }

    @Override
    public List<User> getUsersByType(UserType userType){
        return userRepository.findByUserType(userType);
    }

    @Override
    public Page<UserDTO> getUsersByType(UserTypeDTO request, Pageable pageable){
        UserType userType = request.getUserType();
        Page<User> userPage = userRepository.findByUserType(userType, pageable);
        return userPage.map(UserDTO::from);
    }

    @Override
    public Page<UserDTO> getUsersByCreationTimeAfter(CreationTimeDTO request, Pageable pageable) {
        Page<User> userPage = userRepository.findByCreationTimeAfter(request.getCreationTime(), pageable);
        return userPage.map(UserDTO::from);
    }

    @Override
    public Page<UserDTO> getUsersByCreationTimeBefore(CreationTimeDTO request, Pageable pageable){
        Page<User> userPage = userRepository.findByCreationTimeBefore(request.getCreationTime(), pageable);
        return userPage.map(UserDTO::from);
    }

    public Page<UserDTO> getAllUsers(Pageable pageable){
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserDTO::from);
    }

    public List<ProjectDTO> getAllProjectsForCurrentUser (){
        User user = authService.getCurrentUser();
        return user.getAccessibleProjects().stream().map(ProjectDTO::from).toList();
    }

    //MODIFIERS

    @Override
    public void updateUsernameForLoggedInUser(UsernameDTO request) {
        String username = request.getUsername();
        if (!ValidationUtils.isValidUsername(username)){
            throw new IllegalArgumentException("Invalid username");
        }
        User user = authService.getCurrentUser();
        user.setUsername(username);
        userRepository.save(user);
    }

    @Override
    public void updateEmailForLoggedInUser(EmailDTO request) {
        String email = request.getEmail();
        if (!ValidationUtils.isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email");
        }
        User user = authService.getCurrentUser();
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void updatePasswordForLoggedInUser(UpdatePasswordDTO request) {
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
        User user = getUserByUoid(uoid);
        if (!ValidationUtils.isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email");
        }
        user.setEmail(email);
        userRepository.save(user);
    }
}
