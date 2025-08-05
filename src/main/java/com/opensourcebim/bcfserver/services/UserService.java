package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.CreationTimeDTO;
import com.opensourcebim.bcfserver.dtos.CreationTimeRangeDTO;
import com.opensourcebim.bcfserver.dtos.user.*;
import com.opensourcebim.bcfserver.dtos.project.ProjectDTO;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    //Getters

    UserDTO getUserByUsername(UsernameDTO request);

    boolean existsByUsername(String username);

    UserDTO getUserByEmail(EmailDTO request);

    boolean existsByEmail(String email);

    UserDTO getUserByUoid(UoidDTO request);

    User getUserByUoid(Long uoid);

    List<User> getUsersByType(UserType userType);

    Page<UserDTO> getUsersByType(UserTypeDTO request, Pageable pageable);

    Page<UserDTO> getUsersByCreationTimeAfter(CreationTimeDTO request, Pageable pageable);

    Page<UserDTO> getUsersByCreationTimeBefore(CreationTimeDTO request, Pageable pageable);

    Page<UserDTO> getUsersByCreationTimeRange(CreationTimeRangeDTO request, Pageable pageable);

    Page<UserDTO> getAllUsers(Pageable pageable);

    Page<ProjectDTO> getAllProjectsForCurrentUser(Pageable pageable);

    //Modification

    void updateUsernameForLoggedInUser(UsernameDTO request);

    void updateEmailForLoggedInUser(EmailDTO request);

    void updatePasswordForLoggedInUser(UpdatePasswordDTO request);

    void updateEmailForUser(Long uoid, String email);
}
