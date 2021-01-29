/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Common.ConsoleColors;
import Utilities.UserDataIO;
import Utilities.Validate;
import java.io.IOException;
import Doctor.Doctor;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AdminController {

    UserDataIO userDataIO;
    Validate validate;

    ArrayList<Doctor> doctors;

    public AdminController() {
        userDataIO = new UserDataIO();
        validate = new Validate();
        doctors = new ArrayList<>();
        
    }

    public void processing() throws IOException {
        
        while (true) {
            String usercode = validate.getString("Enter usercode: ");
            Doctor doctor = userDataIO.getDoctorByUserCode(usercode);
            if (doctor == null) {
                System.out.println("Doctor is not exist!!!");
                System.out.println("");
                continue;
            }

            System.out.println(ConsoleColors.BLUE_BACKGROUND + "LIST PATIENT");
            System.out.println(String.format("%-10s|%-10s|%-10s|%-20s|%-20s", "ID", "NAME", "DESEASE TYPE", "CONSULT DATE", "CONSULT NOTE"));
            doctor.getPatients().forEach((patient) -> {
                System.out.println(patient);
            });
            System.out.println("");

            printMENU_AddUpdatePatient();
            int choice = validate.getINT_LIMIT("Enter choice: ", 1, 2);
            switch (choice) {
                case 1:
                    addNewPatient(doctor);
                    break;
                case 2:
                    updateAPatient(doctor);
                    break;
            }
            break;
        }
    }

    private void printMENU_AddUpdatePatient() {
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1\tAdd new a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "2\tUpdate a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
    }

    private void addNewPatient(Doctor doctor) throws IOException {
        while (true) {
            int patientid = validate.getINT_LIMIT("Enter patient id: ", 1, Integer.MAX_VALUE);
            
        }

    }

    private void updateAPatient(Doctor doctor) {

    }

    
}
