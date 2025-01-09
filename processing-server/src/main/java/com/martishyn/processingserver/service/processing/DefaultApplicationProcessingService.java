package com.martishyn.processingserver.service.processing;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.martishyn.processingserver.domain.Application;
import com.martishyn.processingserver.domain.Status;
import com.martishyn.processingserver.service.ApplicationsFetchService;
import com.martishyn.processingserver.service.validation.ApplicationValidationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultApplicationProcessingService implements ApplicationProcessingService {

    private final ApplicationsFetchService applicationsFetchService;

    private final ObjectMapper objectMapper;

    private final ApplicationValidationService applicationValidationService;

    @Value("${processing.batch.size}")
    private static int BATCH_SIZE;

    @PostConstruct
    @Override
    public void processApplications() throws IOException {
        JsonParser jsonParser = null;
        List<Application> applications = new ArrayList<>();
        try {
            jsonParser = objectMapper.getFactory().createParser(applicationsFetchService.fetchApplications());
            if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    Application processedApplication = jsonParser.readValueAs(Application.class);
                    if (applicationValidationService.isValid(processedApplication)){
                        processedApplication.setStatus(Status.APPROVED);
                    } else {
                        processedApplication.setStatus(Status.REJECTED);
                    }
                    applications.add(processedApplication);
                    if (BATCH_SIZE == applications.size()) {
                        //some service -> process
                        applications.clear();
                    }
                }
                if (!applications.isEmpty()){
                    //some service -> process the rest of entities
                }
            }
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
        } finally {
            jsonParser.close();
        }
    }
}

