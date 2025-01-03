package com.martishyn.mockserver.provider;

import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class JsonFileProvider extends AbstractPathProvider{

    @Value("${applications.data.file.json}")
    private String jsonFileName;

    @Override
    public Path getApplicationsFile() {
        return getFilePath(jsonFileName);
    }
}
