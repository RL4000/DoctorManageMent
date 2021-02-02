/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Common.Patient;
import Common.UserRole;
import User.User;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ValidationAdminManager {

    public Patient getPatientByPatientID(int patientid, ArrayList<Patient> patients) {
        for (Patient patient : patients) {
            if (patient.getPatientId() == patientid) {
                return patient;
            }
        }
        return null;
    }

    public User getDoctorByUserCode(String usercode, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUserRole() == UserRole.AUTHORIZED_DOCTOR 
                    && usercode.equals(user.getUserCode())) {
                return user;
            }
        }
        return null;
    }
}
