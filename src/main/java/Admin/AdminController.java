/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Common.ConsoleColors;
import Common.Patient;
import Common.UserRole;
import Utilities.Validate;
import java.io.IOException;
import Doctor.Doctor;
import User.User;
import Utilities.UserDataIO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class AdminController {

    Validate validate;
    ValidationAdminManager adminManager;
    UserDataIO userDataIO;

    ArrayList<User> listUsers;
    ArrayList<Doctor> listDoctors;
    ArrayList<Patient> listPatients;
    Doctor doctorGotByUserCode;

    public AdminController() {
        validate = new Validate();
        adminManager = new ValidationAdminManager();
        listDoctors = new ArrayList<>();
        userDataIO = new UserDataIO();
        listPatients = new ArrayList<>();
        listUsers = new ArrayList<>();
    }

    public void processing() throws IOException {

        listUsers.add(new Doctor("1", "doctor1", "doctor1", UserRole.AUTHORIZED_DOCTOR));
        listUsers.add(new Doctor("doctor2", "doctor2", UserRole.DOCTOR));
        listUsers.add(new Doctor("3", "doctor3", "doctor3", UserRole.AUTHORIZED_DOCTOR));
        listUsers.add(new Doctor("doctor4", "doctor4", UserRole.DOCTOR));
        listUsers.add(new Doctor("5", "doctor5", "doctor5", UserRole.AUTHORIZED_DOCTOR));
        listUsers.add(new Doctor("doctor6", "doctor6", UserRole.DOCTOR));
        listUsers.add(new Doctor("7", "doctor7", "doctor7", UserRole.AUTHORIZED_DOCTOR));
        listUsers.add(new Doctor("doctor8", "doctor8", UserRole.DOCTOR));

        userDataIO.writeDataUser(listUsers);

        while (true) {
            listUsers = userDataIO.readDataUser();
            System.out.println(ConsoleColors.BLUE_BOLD + "LIST DOCTOR");
            for (User user : listUsers) {
                if (user.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                    System.out.println((Doctor) user);
                }
            }
            System.out.println("");

            String usercode = validate.getString("Enter usercode: ");
            doctorGotByUserCode = (Doctor) adminManager.getDoctorByUserCode(usercode, listUsers);

            if (doctorGotByUserCode == null) {
                System.out.println("Doctor is not exist!!!");
                System.out.println("");
                continue;
            }

            listPatients = doctorGotByUserCode.getPatients();

            if (listPatients.isEmpty()) {
                System.out.println(ConsoleColors.RED + "List patient's this doctor is emty");
            } else {
                System.out.println(ConsoleColors.BLUE_BOLD + "LIST PATIENT");
                System.out.println(String.format("%-10s|%-10s|%-10s|%-20s|%-20s", "ID", "NAME", "DESEASE TYPE", "CONSULT DATE", "CONSULT NOTE"));
                listPatients.forEach((patient) -> {
                    System.out.println(patient);
                });
                System.out.println("");
            }

            printMENU_AddUpdatePatient();
            int choice = validate.getINT_LIMIT("Enter choice: ", 1, 2);
            switch (choice) {
                case 1:
                    addNewPatient();
                    for (User user : listUsers) {
                        if (user.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                            System.out.println((Doctor) user);
                        }
                    }
                    userDataIO.writeDataUser(listUsers);
                    break;
                case 2:
                    updateAPatient();
                    userDataIO.writeDataUser(listUsers);
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
            Patient patient = adminManager.getPatientByPatientID(patientid, listPatients);
            if (patient != null) {
                System.out.println(ConsoleColors.RED + "ID exist");
                System.out.println("");
                continue;
            }

            String name = validate.getString("Enter name: ");
            String diseaseType = validate.getString("Enter diseaseType: ");
            Date consultDate = validate.getDate_LimitToCurrent("Enter consultDate: ");
            String consultNote = validate.getString("Enter consultNote: ");

            listPatients.add(new Patient(patientid, name, diseaseType, consultDate, consultNote));
                
            break;
        }

    }

    private void updateAPatient() throws IOException {
        while (true) {
            int patientid = validate.getINT_LIMIT("Enter patient id: ", 1, Integer.MAX_VALUE);
            Patient patient = adminManager.getPatientByPatientID(patientid, listPatients);
            if (patient == null) {
                System.out.println(ConsoleColors.RED + "ID is not exist");
                continue;
            }

            String newName = validate.getString("Enter name: ");
            String newDiseaseType = validate.getString("Enter diseaseType: ");
            Date newConsultDate = validate.getDate_LimitToCurrent("Enter consultDate: ");
            String newConsultNote = validate.getString("Enter consultNote: ");

            patient.setName(newName);
            patient.setDiseaseType(newDiseaseType);
            patient.setConsultDate(newConsultDate);
            patient.setConsultNote(newConsultNote);
            break;
        }
    }
}
