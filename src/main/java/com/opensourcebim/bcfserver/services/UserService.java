package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.EmailUpdateForUserRequestDTO;
import com.opensourcebim.bcfserver.dtos.RegisterRequestDTO;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //Retrieval

    User getCurrentUser();

    Optional<User> getUserByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);

    List<User> getUsersByType(UserType userType);

    Page<User> getAllUsers(Pageable pageable);

    //Modification

    void updateEmailForLoggedInUser(String email);

    void updateEmailForUser(EmailUpdateForUserRequestDTO request);
}
