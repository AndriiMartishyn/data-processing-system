package com.martishyn.processingserver;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessingServerApplication {

    public static void main(String[] args) {
        FluentConfiguration configure = Flyway.configure();
        SpringApplication.run(ProcessingServerApplication.class, args);


    }
}
