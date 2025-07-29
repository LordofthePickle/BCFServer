package com.opensourcebim.bcfserver;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BCFServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BCFServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner initApp() {
        return args -> {
            System.out.println("Commandline has started.");
        };
    }

    @Bean
    public ApplicationRunner runOnStartup() {
        return args -> {
            System.out.println("BCF Server has started.");
        };
    }

}
