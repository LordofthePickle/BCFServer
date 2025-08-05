package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.auth.ForgotPasswordDTO;
import com.opensourcebim.bcfserver.dtos.auth.LoginRequestDTO;
import com.opensourcebim.bcfserver.dtos.auth.PasswordResetDTO;
import com.opensourcebim.bcfserver.dtos.auth.RegisterRequestDTO;
import com.opensourcebim.bcfserver.models.User;

public interface AuthService {

    User registerUser(RegisterRequestDTO request);

    String loginUser(LoginRequestDTO request);

    void logoutUser();

    void forgotPassword(ForgotPasswordDTO request);

    void resetPassword(PasswordResetDTO request);

    User getCurrentUser();
}
