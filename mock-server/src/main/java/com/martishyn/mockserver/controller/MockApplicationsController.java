package com.martishyn.mockserver.controller;

import com.martishyn.mockserver.provider.AbstractPathProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("v1/api/applications")
@RequiredArgsConstructor
public class MockApplicationsController {

    private final AbstractPathProvider pathProvider;

    @GetMapping("/json-data")
    public ResponseEntity<StreamingResponseBody> getApplicationsInJson() {
        Path jsonFilePath = pathProvider.getApplicationsFile();
        StreamingResponseBody jsonContent = output -> Files.copy(jsonFilePath, output);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(jsonFilePath.toFile().length())
                .body(jsonContent);
    }
}
