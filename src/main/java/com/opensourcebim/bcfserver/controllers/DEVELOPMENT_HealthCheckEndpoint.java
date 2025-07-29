package com.opensourcebim.bcfserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DEVELOPMENT_HealthCheckEndpoint {

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}
