package com.opensourcebim.bcfserver.dtos.user;

public class UpdatePasswordDTO {
    private String oldPassword;
    private String newPassword;


    public UpdatePasswordDTO() {}

    public String getOldPassword() {
        return oldPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
