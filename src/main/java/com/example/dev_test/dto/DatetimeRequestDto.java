package com.example.dev_test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class DatetimeRequestDto {

    @JsonFormat(timezone = "Asia/Seoul")
    private Timestamp startTime;

    @JsonFormat(timezone = "Asia/Seoul")
    private Timestamp endTime;

    public DatetimeRequestDto(Timestamp startTime, Timestamp endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
