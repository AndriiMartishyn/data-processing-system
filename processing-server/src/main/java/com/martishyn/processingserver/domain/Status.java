package com.martishyn.processingserver.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("Pending")
    PENDING,
    @JsonProperty("Approved")
    APPROVED,
    @JsonProperty("Flagged")
    FLAGGED,
    @JsonProperty("Rejected")
    REJECTED;

    public String getStatus() {
        return this.name();
    }
}
