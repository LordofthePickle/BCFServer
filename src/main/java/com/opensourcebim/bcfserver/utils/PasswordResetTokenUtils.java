package com.opensourcebim.bcfserver.utils;

import java.util.UUID;

public class PasswordResetTokenUtils {

    static public String generateToken() {
        return UUID.randomUUID().toString();
    }

}
