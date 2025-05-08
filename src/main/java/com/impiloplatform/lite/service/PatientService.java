package com.impiloplatform.lite.service;

import com.impiloplatform.lite.dbaccess.PatientDbAccess;
import com.impiloplatform.lite.model.Device;
import com.impiloplatform.lite.model.Patient;
import com.impiloplatform.lite.model.Reading;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientDbAccess patientDbAccess;

    public PatientService(PatientDbAccess patientDbAccess) {
        this.patientDbAccess = patientDbAccess;
    }

    public List<Patient> listPatients() {
        return this.patientDbAccess.listPatients();
    }

//    public List<Reading> listReadings() {
//        return this.patientDbAccess.listReadings();
//    }

    public Patient addPatient(Patient newPatient) {
        return patientDbAccess.addPatient(newPatient);
    }

    public Patient getPatient(Long id) {
        return patientDbAccess.getPatient(id);
    }

    public Patient editPatient(Long id, Patient patient) {
        return patientDbAccess.editPatient(id, patient);
    }

    public Device addDevice(Long patientId, Device device) {
        return patientDbAccess.addDevice(patientId, device);
    }

    public Reading addReading(Long deviceId, Reading reading) {
        return patientDbAccess.addReading(deviceId, reading);
    }

    public void deletePatient(Long id) {
        patientDbAccess.deletePatient(id);
    }

    public void deleteDevice(Long id) {
        patientDbAccess.deleteDevice(id);
    }

}
