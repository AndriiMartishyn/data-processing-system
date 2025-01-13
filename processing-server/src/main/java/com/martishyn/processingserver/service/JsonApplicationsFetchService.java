package com.martishyn.processingserver.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class JsonApplicationsFetchService implements ApplicationsFetchService{

    private static final String MOCK_API_URL = "http://localhost:8011/v1/api/applications/json-data";

    public InputStream fetchApplications() throws IOException {
            URL fetchUrl = new URL(MOCK_API_URL);
            HttpURLConnection connection = (HttpURLConnection) fetchUrl.openConnection();
            connection.setRequestMethod(String.valueOf(HttpMethod.GET));
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();
            return connection.getInputStream();
        }
}
