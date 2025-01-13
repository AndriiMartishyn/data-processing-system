package com.martishyn.processingserver.service.processing;

import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public interface ApplicationProcessingService {

    void processApplications() throws IOException;
}
