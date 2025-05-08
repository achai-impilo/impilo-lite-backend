package com.impiloplatform.lite.model;

import java.util.List;

public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dob;
    private String address;
    private List<Device> devices;

    // get and set for ID

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // get and set for firstName

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // get and set for lastName

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // get and set for email

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // get and set for phoneNumber

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // get and set for dob

    public String getDOB() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    // get and set for address

    public String getAddress() { return address;}
    public void setAddress(String address) { this.address = address; }

    // get and set for devices

    public List<Device> getDevices() { return devices; }
    public void setDevices(List<Device> devices) { this.devices = devices; }

}
