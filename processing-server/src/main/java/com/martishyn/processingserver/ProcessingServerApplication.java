package com.martishyn.processingserver;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ProcessingServerApplication {

    private final Service service;

    public static void main(String[] args) {
        SpringApplication.run(ProcessingServerApplication.class, args);
    }

}
