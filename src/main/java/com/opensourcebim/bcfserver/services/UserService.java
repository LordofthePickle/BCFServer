package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.ProjectDTO;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //Getters

    User getCurrentUser();

    Optional<User> getUserByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> getUserByUoid(Long uoid);

    List<User> getUsersByType(UserType userType);

    Page<User> getAllUsers(Pageable pageable);

    List<ProjectDTO> getAllProjectsForCurrentUser();

    //Modification

    void updateEmailForLoggedInUser(String email);

    void updateEmailForUser(Long uoid, String email);
}
