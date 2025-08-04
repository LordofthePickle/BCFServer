package com.opensourcebim.bcfserver.utils;

import java.util.UUID;

public class TokenUtils {

    static public String generateToken() {
        return UUID.randomUUID().toString();
    }

}
