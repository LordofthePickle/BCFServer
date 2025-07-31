package com.opensourcebim.bcfserver.dtos;

import com.opensourcebim.bcfserver.models.User;

public class UserDTO {
    private Long uoid;
    private String username;
    private String email;
    private String userTyoe;

    public UserDTO(User user) {
        this.uoid = user.getUoid();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.userTyoe = user.getUserType().name();
    }

    public Long getUoid() {
        return uoid;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getUserTyoe() {
        return userTyoe;
    }
}
