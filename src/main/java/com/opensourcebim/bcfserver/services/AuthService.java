package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.ForgotPasswordDTO;
import com.opensourcebim.bcfserver.dtos.LoginRequestDTO;
import com.opensourcebim.bcfserver.dtos.PasswordResetDTO;
import com.opensourcebim.bcfserver.dtos.RegisterRequestDTO;
import com.opensourcebim.bcfserver.models.User;

public interface AuthService {

    User registerUser(RegisterRequestDTO request);

    String loginUser(LoginRequestDTO request);

    void logoutUser();

    void forgotPassword(ForgotPasswordDTO request);

    void resetPassword(PasswordResetDTO request);

    User getCurrentUser();
}
