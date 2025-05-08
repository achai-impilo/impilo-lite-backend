package com.impiloplatform.lite.dbaccess;

import com.impiloplatform.lite.model.Device;
import com.impiloplatform.lite.model.Patient;
import com.impiloplatform.lite.model.Reading;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PatientDbAccess {

    private final JdbcTemplate jdbcTemplate;

    public PatientDbAccess(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Patient> listPatients(){
        String sql = "SELECT * FROM patients ORDER BY id";
        return this.jdbcTemplate.query(sql, (rs, rowNum) -> {
                    Patient p = new Patient();
                    p.setId(rs.getLong("id"));
                    p.setFirstName(rs.getString("first_name"));
                    p.setLastName(rs.getString("last_name"));
                    p.setEmail(rs.getString("email"));
                    p.setPhoneNumber(rs.getString("phone_number"));
                    p.setDob(rs.getString("dob"));
                    p.setAddress(rs.getString("address"));

                    String deviceSql = "SELECT * FROM devices WHERE patient_id = ?";
                    List<Device> devices = jdbcTemplate.query(deviceSql, new Object[]{p.getId()}, (deviceResultSet, deviceRowNum) -> {
                        Device device = new Device();
                        device.setDeviceId(deviceResultSet.getLong("device_id"));
                        device.setPatientId(deviceResultSet.getLong("patient_id"));
                        device.setDeviceName(deviceResultSet.getString("device_name"));
                        device.setDeviceSerialNumber(deviceResultSet.getString("device_serialnumber"));

                        String readingsSql = "SELECT * FROM readings WHERE device_id = ?";
                        List<Reading> readings = jdbcTemplate.query(readingsSql, new Object[]{deviceResultSet.getLong("device_id")}, (readingResultSet, readingRowNum) -> {
                            Reading reading = new Reading();
                            reading.setReadingId(readingResultSet.getLong("reading_id"));
                            reading.setDeviceId(readingResultSet.getLong("device_id"));
                            reading.setSystolicBP(readingResultSet.getString("systolic_bp"));
                            reading.setDiastolicBP(readingResultSet.getString("diastolic_bp"));
                            reading.setHeartRate(readingResultSet.getString("heart_rate"));
//                reading.setRecordedAt(readingResultSet.getTimestamp("recorded_at").toLocalDateTime());
                            reading.setValue(readingResultSet.getString("value"));
                            return reading;
                        });

                        device.setReadings(readings);
                        return device;
                    });

                    p.setDevices(devices);
                    return p;
                }
        );
    }

    public Patient addPatient(Patient patient) {
        final String INSERT_PATIENT = "INSERT INTO patients (first_name, last_name, email, phone_number, dob, address) VALUES (?, ?, ?, ?, ?, ?)";

        this.jdbcTemplate.update(INSERT_PATIENT, patient.getFirstName(), patient.getLastName(), patient.getEmail(),
                patient.getPhoneNumber(), java.sql.Date.valueOf(patient.getDOB()), patient.getAddress());

        return patient;
    }

    public Patient getPatient(Long Id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{Id}, (resultSet, rowNum) -> {
            Patient patient = new Patient();
            patient.setId(resultSet.getLong("id"));
            patient.setFirstName(resultSet.getString("first_name"));
            patient.setLastName(resultSet.getString("last_name"));
            patient.setEmail(resultSet.getString("email"));
            patient.setPhoneNumber(resultSet.getString("phone_number"));
            patient.setDob(resultSet.getString("dob"));
            patient.setAddress(resultSet.getString("address"));

            String deviceSql = "SELECT * FROM devices WHERE patient_id = ?";
            List<Device> devices = jdbcTemplate.query(deviceSql, new Object[]{Id}, (deviceResultSet, deviceRowNum) -> {
                Device device = new Device();
                device.setDeviceId(deviceResultSet.getLong("device_id"));
                device.setPatientId(deviceResultSet.getLong("patient_id"));
                device.setDeviceName(deviceResultSet.getString("device_name"));
                device.setDeviceSerialNumber(deviceResultSet.getString("device_serialnumber"));

                String readingsSql = "SELECT * FROM readings WHERE device_id = ?";
                List<Reading> readings = jdbcTemplate.query(readingsSql, new Object[]{deviceResultSet.getLong("device_id")}, (readingResultSet, readingRowNum) -> {
                    Reading reading = new Reading();
                    reading.setReadingId(readingResultSet.getLong("reading_id"));
                    reading.setDeviceId(readingResultSet.getLong("device_id"));
                    reading.setSystolicBP(readingResultSet.getString("systolic_bp"));
                    reading.setDiastolicBP(readingResultSet.getString("diastolic_bp"));
                    reading.setHeartRate(readingResultSet.getString("heart_rate"));
//                reading.setRecordedAt(readingResultSet.getTimestamp("recorded_at").toLocalDateTime());
                    reading.setValue(readingResultSet.getString("value"));
                    return reading;
                });

                device.setReadings(readings);
                return device;
            });

            patient.setDevices(devices);
            return patient;
        });
    }

    public Patient editPatient(Long id, Patient patient) {
        final String UPDATE_PATIENT = "UPDATE patients SET first_name = ?, last_name = ?, email = ?, phone_number = ?, dob = ?, address = ? WHERE id = ?";
        final String SELECT_UPDATED_PATIENT = "SELECT id, first_name, last_name, email, phone_number, dob, address FROM patients WHERE id = ?";

        this.jdbcTemplate.update(UPDATE_PATIENT, patient.getFirstName(), patient.getLastName(), patient.getEmail(), patient.getPhoneNumber(), java.sql.Date.valueOf(patient.getDOB()), patient.getAddress(), id);

        Patient updatedPatient = jdbcTemplate.queryForObject(SELECT_UPDATED_PATIENT, new Object[]{patient.getId()}, (resultSet, rowNum) -> {
            Patient p = new Patient();
            p.setId(resultSet.getLong("id"));
            p.setFirstName(resultSet.getString("first_name"));
            p.setLastName(resultSet.getString("last_name"));
            p.setEmail(resultSet.getString("email"));
            p.setPhoneNumber(resultSet.getString("phone_number"));
            p.setDob(resultSet.getString("dob"));
            p.setAddress(resultSet.getString("address"));
            return p;

        });

        return updatedPatient;
    }

    public Device addDevice(Long patientId, Device device) {
        final String INSERT_DEVICE = "INSERT INTO devices (patient_id, device_name, device_serialnumber) VALUES (?, ?, ?) RETURNING device_id";

        Long generatedId = this.jdbcTemplate.queryForObject(INSERT_DEVICE, new Object[]{patientId, device.getDeviceName(), device.getDeviceSerialNumber()}, Long.class);

        device.setDeviceId(generatedId);
        device.setPatientId(patientId);

        return device;
    }

    public Reading addReading(Long deviceId, Reading reading) {
        final String INSERT_READING = "INSERT INTO readings (device_id, systolic_bp, diastolic_bp, heart_rate, value) VALUES (?, ?, ?, ?, ?) RETURNING reading_id";

        Long generatedId = this.jdbcTemplate.queryForObject(INSERT_READING, new Object[]{deviceId, reading.getSystolicBP(), reading.getDiastolicBP(), reading.getHeartRate(), reading.getValue()}, Long.class);
        reading.setReadingId(generatedId);
        reading.setDeviceId(deviceId);

        return reading;
    }

    public void deletePatient(Long id) {
        String query = "DELETE FROM patients WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    public void deleteDevice(Long id) {
        String query = "DELETE FROM devices WHERE device_id = ?";
        jdbcTemplate.update(query, id);
    }

}

// table 1: patients
// table 2: devices
// table 3: readings
