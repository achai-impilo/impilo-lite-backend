package com.impiloplatform.lite.controller;

import com.impiloplatform.lite.model.Device;
import com.impiloplatform.lite.model.Patient;
import com.impiloplatform.lite.model.Reading;
import com.impiloplatform.lite.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class Controllers {

    private final PatientService patientService;

    public Controllers(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome to Impilo Lite!";
    }

    @GetMapping("/patients")
    public List<Patient> listPatients(){
        return this.patientService.listPatients();
    }

//    @GetMapping("/readings")
//    public List<Reading> listReadings() {
//        return this.patientService.listReadings();
//    }

    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return this.patientService.getPatient(id);
    }
    // Post Request for adding patients
    @PostMapping("/patients")
    public Patient addPatient(@RequestBody final Patient newPatient) {
        return patientService.addPatient(newPatient);
    }

    @PutMapping("/edit-patient/{id}")
    public Patient editPatient(@PathVariable Long id, @RequestBody Patient patient) {
        return this.patientService.editPatient(id, patient);
    }

    // add-device functionality
    @PostMapping("/add-device/{patientId}")
    public Device addDevice(@PathVariable Long patientId, @RequestBody Device device) {
        return this.patientService.addDevice(patientId, device);
    }

    @PostMapping("/add-readings/{deviceId}")
    public Reading addReading(@PathVariable Long deviceId, @RequestBody Reading reading) {
        return this.patientService.addReading(deviceId, reading);
    }

    // TODO: delete request
    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable Long id) {
        this.patientService.deletePatient(id);
    }

//    @DeleteMapping("/patients/{id}")
//    public void deleteDevice(@PathVariable Long id) {
//        this.patientService.deleteDevice(id);
//    }
}

// QUERY TO DELETE ALL DATA IN TABLES
//DELETE FROM readings;
//DELETE FROM devices;
//DELETE FROM patients;

// QUERY TO RESET IDs
//ALTER SEQUENCE readings_reading_id_seq RESTART WITH 1;
//ALTER SEQUENCE devices_device_id_seq RESTART WITH 1;
//ALTER SEQUENCE patients_id_seq RESTART WITH 1;