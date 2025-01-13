package com.martishyn.processingserver.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Application {

    private Long applicationId;

    private Student student;

    private String course;

    private String semester;

    private Status status;

}
