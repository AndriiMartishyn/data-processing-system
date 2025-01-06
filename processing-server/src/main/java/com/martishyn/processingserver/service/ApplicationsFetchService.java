package com.martishyn.processingserver.service;

import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;

public interface ApplicationsFetchService {

    InputStream fetchApplications() throws IOException;
}
