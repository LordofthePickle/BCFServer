package com.opensourcebim.bcfserver.dtos.auth;

public class PasswordResetDTO {
    private String token;
    private String newPassword;

    public PasswordResetDTO() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
