package com.martishyn.mockserver.provider;

import java.net.URL;
import java.nio.file.Path;

public abstract class AbstractPathProvider {

    public abstract Path getApplicationsFile();

    protected Path getFilePath(String fileName) {
        URL resource = this.getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        return Path.of(resource.getPath());
    }
}
