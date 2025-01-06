package com.martishyn.processingserver.service.processing;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.martishyn.processingserver.domain.Application;
import com.martishyn.processingserver.service.ApplicationsFetchService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultApplicationProcessingService implements ApplicationProcessingService {

    private final ApplicationsFetchService applicationsFetchService;
    private final ObjectMapper objectMapper;
    private long appCount = 0;
    private Set<Application> applications = new HashSet<>();

    @PostConstruct
    @Override
    public void processApplications() throws IOException {
        JsonParser jsonParser = null;
        try {
            jsonParser = objectMapper.getFactory().createParser(applicationsFetchService.fetchApplications());
            if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    Application application = jsonParser.readValueAs(Application.class);
                    appCount++;
                    applications.add(application);
                }
            }
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
        } finally {
            jsonParser.close();
        }
        System.out.println(appCount);
    }
}

