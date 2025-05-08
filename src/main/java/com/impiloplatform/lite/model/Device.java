package com.impiloplatform.lite.model;

import java.util.List;

public class Device {
    private Long deviceId;
    private Long patientId;
    private String deviceName;
    private List<Reading> readings;
    private String deviceSerialNumber;

    // get and set for deviceId

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    // get and set for patientId

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    // get and set for deviceName

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    // get and set for readings

    public List<Reading> getReadings() { return readings; }
    public void setReadings(List<Reading> readings) { this.readings = readings; }

    public String getDeviceSerialNumber() { return deviceSerialNumber; }
    public void setDeviceSerialNumber(String deviceSerialNumber) { this.deviceSerialNumber = deviceSerialNumber; }

}
