package com.martishyn.processingserver.service.validation;

import com.martishyn.processingserver.domain.Application;

public interface ApplicationValidationService {

    boolean isValid(Application application);
}
