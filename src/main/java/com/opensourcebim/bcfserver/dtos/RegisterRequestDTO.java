package com.opensourcebim.bcfserver.dtos;

import com.opensourcebim.bcfserver.models.enums.UserType;

public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private UserType userType;

    public String getUsername() {
        return username;
    }

    public CharSequence getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }
}
