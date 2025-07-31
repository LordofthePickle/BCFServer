package com.opensourcebim.bcfserver.services;

import com.opensourcebim.bcfserver.dtos.LoginRequestDTO;
import com.opensourcebim.bcfserver.dtos.RegisterRequestDTO;
import com.opensourcebim.bcfserver.models.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    User registerUser(RegisterRequestDTO request);

    String loginUser(LoginRequestDTO request);
}
