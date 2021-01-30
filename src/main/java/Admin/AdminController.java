/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Common.ConsoleColors;
import Common.Patient;
import Utilities.Validate;
import java.io.IOException;
import Doctor.Doctor;
import Utilities.DoctorDataIO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class AdminController {

    DoctorDataIO doctorDataIO;
    Validate validate;
    Doctor doctorGotByUserCode;
    ArrayList<Patient> patients;
    ValidationAdminManager adminManager;
    ArrayList<Doctor> doctors;

    public AdminController() {
        doctorDataIO = new DoctorDataIO();
        validate = new Validate();
        adminManager = new ValidationAdminManager();
    }

    public void processing() throws IOException {

        while (true) {
            String usercode = validate.getString("Enter usercode: ");
            doctors = doctorDataIO.readDataDoctor();
            doctorGotByUserCode = adminManager.getDoctorByUserCode(usercode, doctors);

            if (doctorGotByUserCode == null) {
                System.out.println("Doctor is not exist!!!");
                System.out.println("");
                continue;
            }

            patients = doctorGotByUserCode.getPatients();

            System.out.println(ConsoleColors.BLUE_BACKGROUND + "LIST PATIENT");
            System.out.println(String.format("%-10s|%-10s|%-10s|%-20s|%-20s", "ID", "NAME", "DESEASE TYPE", "CONSULT DATE", "CONSULT NOTE"));
            patients.forEach((patient) -> {
                System.out.println(patient);
            });
            System.out.println("");

            printMENU_AddUpdatePatient();
            int choice = validate.getINT_LIMIT("Enter choice: ", 1, 2);
            switch (choice) {
                case 1:
                    addNewPatient();
                    break;
                case 2:
                    updateAPatient();
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

    private void addNewPatient() throws IOException {
        while (true) {
            int patientid = validate.getINT_LIMIT("Enter patient id: ", 1, Integer.MAX_VALUE);
            Patient patient = adminManager.idExist(patientid, patients);
            if (patient != null) {
                System.out.println(ConsoleColors.RED + "ID exist");
                System.out.println("");
                continue;
            }

            String name = validate.getString("Enter name: ");
            String diseaseType = validate.getString("Enter diseaseType: ");
            Date consultDate = validate.getDate_LimitToCurrent("Enter consultDate: ");
            String consultNote = validate.getString("Enter consultNote: ");

            patients.add(new Patient(patientid, name, name, consultDate, name));
            break;
        }

    }

    private void updateAPatient() throws IOException {
        while (true) {
            int patientid = validate.getINT_LIMIT("Enter patient id: ", 1, Integer.MAX_VALUE);
            Patient patient = adminManager.idExist(patientid, patients);
            if (patient == null) {
                System.out.println("ID is not exist");
                continue;
            }
            
            String name = validate.getString("Enter name: ");
            String diseaseType = validate.getString("Enter diseaseType: ");
            Date consultDate = validate.getDate_LimitToCurrent("Enter consultDate: ");
            String consultNote = validate.getString("Enter consultNote: ");
            
            patient.setName(name);
            patient.setDiseaseType(diseaseType);
            patient.setConsultDate(consultDate);
            patient.setConsultNote(consultNote);
            break;

        }

    }

}
