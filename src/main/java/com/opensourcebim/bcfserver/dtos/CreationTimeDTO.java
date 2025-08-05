package com.opensourcebim.bcfserver.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CreationTimeDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;

    public CreationTimeDTO() {}

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
