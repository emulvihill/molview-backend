package com.snazzyrobot.molviewbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(dateTimeProviderRef = "offsetDateTimeProvider")
public class MolViewBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MolViewBackendApplication.class, args);
    }
}
