package com.impiloplatform.lite.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reading {
    private Long  readingId;
    private Long deviceId;
    private String systolicBP;
    private String diastolicBP;
    private String heartRate;
//    private LocalDateTime recordedAt;
    private String value;

    // get and set for readings ID
    public Long getReadingId() {
        return readingId;
    }
    public void setReadingId(Long readingId) {
        this.readingId = readingId;
    }

    // get and set for device ID
    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    // get and set for systolicBP
    public String getSystolicBP() {
        return systolicBP;
    }
    public void setSystolicBP(String systolicBP) {
        this.systolicBP = systolicBP;
    }

    // get and set for diastolic BP
    public String getDiastolicBP() {
        return diastolicBP;
    }
    public void setDiastolicBP(String diastolicBP) {
        this.diastolicBP = diastolicBP;
    }

    // get and set heartRate
    public String getHeartRate() {
        return heartRate;
    }
    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

//    public LocalDateTime getRecordedAt() { return recordedAt; }
//    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

}
