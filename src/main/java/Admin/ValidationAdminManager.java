/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Common.Patient;
import Doctor.Doctor;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ValidationAdminManager {

    Patient idExist(int patientid, ArrayList<Patient> patients) {
        for (Patient patient : patients) {
            if (patient.getPatientId() == patientid) {
                return patient;
            }
        }
        return null;
    }

    Doctor getDoctorByUserCode(String usercode, ArrayList<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            if (usercode.equals(doctor.getUserCode())) {
                return doctor;
            }
        }
        return null;
    }
}
