package com.opensourcebim.bcfserver.dtos.user;

import com.opensourcebim.bcfserver.models.enums.UserType;

public class UserTypeDTO {

    private UserType userType;

    public UserTypeDTO() {}

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
