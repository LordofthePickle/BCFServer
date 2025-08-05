package com.opensourcebim.bcfserver.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CreationTimeRangeDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTimeEnd;

    public CreationTimeRangeDTO() {}

    public LocalDateTime getCreationTimeStart() {
        return creationTimeStart;
    }

    public void setCreationTimeStart(LocalDateTime creationTimeStart) {
        this.creationTimeStart = creationTimeStart;
    }

    public LocalDateTime getCreationTimeEnd() {
        return creationTimeEnd;
    }

    public void setCreationTimeEnd(LocalDateTime creationTimeEnd) {
        this.creationTimeEnd = creationTimeEnd;
    }
}
