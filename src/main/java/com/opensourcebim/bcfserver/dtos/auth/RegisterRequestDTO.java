package com.opensourcebim.bcfserver.dtos.auth;

import com.opensourcebim.bcfserver.models.enums.UserType;

public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private UserType userType;

    public RegisterRequestDTO() {}

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

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
