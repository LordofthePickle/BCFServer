package com.opensourcebim.bcfserver.dtos;

import com.opensourcebim.bcfserver.models.Project;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.UserType;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private String username;
    private String email;
    private UserType userType;
    private String creationTime;
    private String createdBy;
    private List<String> createdUsers;
    private List<String> createdProjects;
    private List<CommentDTO> createdComments;

    public static UserDTO from(User user) {
        UserDTO dto = new UserDTO();
        dto.username = user.getUsername();
        dto.email = user.getEmail();
        dto.userType = user.getUserType();
        dto.creationTime = user.getCreationTime().toString();
        dto.createdBy = user.getCreatedBy().getUsername();
        dto.createdUsers = user.getCreatedUsers().stream().map(User::getUsername).collect(Collectors.toList());
        dto.createdProjects = user.getCreatedProjects().stream().map(Project::getName).collect(Collectors.toList());
        dto.createdComments = user.getComments().stream().map(CommentDTO::from).collect(Collectors.toList());
        return dto;
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public UserType getUserType() {
        return userType;
    }
    public String getCreationTime() {
        return creationTime;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public List<String> getCreatedUsers() {
        return createdUsers;
    }
    public List<String> getCreatedProjects() {
        return createdProjects;
    }
    public List<CommentDTO> getCreatedComments() {
        return createdComments;
    }

}
