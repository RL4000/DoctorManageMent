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
import java.text.SimpleDateFormat;
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
    ArrayList<Patient> listPatients;
    Doctor doctorGotByUserCode;

    SimpleDateFormat dateFormat;

    public AdminController() {
        validate = new Validate();
        adminManager = new ValidationAdminManager();
        userDataIO = new UserDataIO();
        initMemoryData();
        dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
    }

    public void processing() throws IOException {
        //--------Đọc data, xóa sau
        initMemoryData();
        while (true) {
            listUsers = userDataIO.readData();

            System.out.println(ConsoleColors.BLUE_BOLD + "LIST DOCTOR");
            System.out.println(String.format("%-10s|%-20s", "USERCODE", "NAME"));
            for (User user : listUsers) {
                if (user.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                    System.out.println(user);
                }
            }
            System.out.println("");

            while (true) {
                String usercode = validate.getString("Enter usercode: ");
                doctorGotByUserCode = (Doctor) adminManager.getDoctorByUserCode(usercode, listUsers);
                if (doctorGotByUserCode == null) {
                    System.out.println(ConsoleColors.RED + "This usercode does not exist,  enter a new usercode ");
                } else {
                    break;
                }
            }

            listPatients = doctorGotByUserCode.getPatients();

            if (listPatients.isEmpty()) {
                System.out.println(ConsoleColors.RED + "List patient's this doctor is emty");
            } else {
                System.out.println(ConsoleColors.BLUE_BOLD + "LIST PATIENT");
                System.out.println(String.format("%-10s|%-15s|%-15s|%-15s|%-15s", "ID", "NAME", "DESEASE TYPE", "CONSULT DATE", "CONSULT NOTE"));
                listPatients.forEach((patient) -> {
                    System.out.println(patient.toString(dateFormat));
                });
                System.out.println("");
            }

            printMENU_AddUpdatePatient();
            int selection = validate.getINT_LIMIT("Enter selection: ", 1, 2);
            switch (selection) {
                case 1:
                    addNewPatient();
                    userDataIO.writeData(listUsers);
                    break;
                case 2:
                    if (!listPatients.isEmpty()) {
                        updateAPatient();
                        userDataIO.writeData(listUsers);
                    } else {
                        System.out.println("This doctor is not in charge of any patient");
                    }
                    break;
            }
            break;
        }
    }

    private void printMENU_AddUpdatePatient() {
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Add new a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Update a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
    }

    private void addNewPatient() throws IOException {
        while (true) {
            int patientid = validate.getINT_LIMIT("Enter patient id: ", 1, Integer.MAX_VALUE);
            Patient patient = adminManager.getPatientByPatientID(patientid, listPatients);
            if (patient != null) {
                System.out.println(ConsoleColors.RED + "ID exist");
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

    private void initMemoryData() {
        listUsers = new ArrayList<>();
        listPatients = new ArrayList<>();
        doctorGotByUserCode = null;
    }

}
