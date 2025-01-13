package com.martishyn.mockserver.provider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PathProviderTest {

    private static final String WRONG_RESOURCE_NAME = "files/json-dataset1.json";

    private static final String RESOURCE_NAME = "files/json-dataset.json";

    AbstractPathProvider pathProvider = new JsonFileProvider();

    @DisplayName("throws-exception-wrong-path")
    @Test
    void shouldThrowFileNotFoundExceptionWhenWrongPath(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            pathProvider.getFilePath(WRONG_RESOURCE_NAME);
        });
    }

    @DisplayName("throws-exception-null-file")
    @Test
    void shouldThrowFileNotFoundExceptionWhenFileNameIsNull(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            pathProvider.getFilePath(null);
        });
    }

    @DisplayName("returns-file-path")
    @Test
    void shouldReturnFilePathWhenProvidingCorrectFileName() {
        Path actualFilePath = pathProvider.getFilePath(RESOURCE_NAME);

        Assertions.assertNotNull(actualFilePath);
    }
}
