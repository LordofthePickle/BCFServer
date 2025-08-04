package com.opensourcebim.bcfserver.services;

public interface EmailService {

    void sendResetEmail(String toEmail, String token);
}
