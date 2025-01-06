package com.martishyn.processingserver.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@Service
public class JsonApplicationsFetchService implements ApplicationsFetchService{

    private static final String MOCK_API_URL = "http://localhost:8011/v1/api/applications/json-data";

    private final RestClient restCLient = RestClient.create();
    private final WebClient webClient = WebClient.builder().build();

//    @Override
//    public InputStream fetchApplications() throws IOException {
//        clientHttpResponse.getBody();
//        return restCLient.get()
//                .uri(MOCK_API_URL)
//                .header("Content-Type", "application/json")
//                .retrieve()
//                .body(ClientHttpResponse.class).getBody();
//    }
        public InputStream fetchApplications() throws IOException {
            URL fetchUrl = new URL(MOCK_API_URL);
            HttpURLConnection connection = (HttpURLConnection) fetchUrl.openConnection();
            connection.setRequestMethod(String.valueOf(HttpMethod.GET));
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();
            return connection.getInputStream();
        }

    public String readIt(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}
