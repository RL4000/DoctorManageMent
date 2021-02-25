/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor;

import Admin.ValidationAdminManager;
import Common.ConsoleColors;
import Common.Patient;
import User.User;
import utilities.UserDataIO;
import utilities.Validate;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DoctorController {

    Validate validate;
    UserDataIO userDataIO;
    ValidationAdminManager validationAdminManager;

    ArrayList<User> listUsers;
    ArrayList<Patient> listPatients;
    Doctor doctorGotByUserCode;
    SimpleDateFormat dateFormat;

    public DoctorController() {
        validate = new Validate();
        validationAdminManager = new ValidationAdminManager();
        userDataIO = new UserDataIO();
        dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        initMemoryData();
    }

    public void processing(User loggedInUser) throws IOException {
        initMemoryData();
        listUsers = userDataIO.readData();
        doctorGotByUserCode = (Doctor) validationAdminManager.getDoctorByUserCode(loggedInUser.getUserCode(), listUsers);
        listPatients = doctorGotByUserCode.getPatients();

        if (listPatients.isEmpty()) {
            System.out.println(ConsoleColors.RED + "You don't have any patients yet!!!");
            if (validate.getYesNo("Do you want add a new patient(Y/N): ")) {
                addNewPatient();
                userDataIO.writeData(listUsers);
                return;
            }
        } else {
            System.out.println(String.format("%-10s|%-15s|%-15s|%-15s|%-15s", "ID", "NAME", "DESEASE TYPE", "CONSULT DATE", "CONSULT NOTE"));
            for (Patient patient : listPatients) {
                System.out.println(patient.toString(dateFormat));
            }
            System.out.println("");
        }

        printMENU_AddUpdatePatient();
        int choice = validate.getINT_LIMIT("Enter choice: ", 1, 2);
        switch (choice) {
            case 1:
                addNewPatient();
                userDataIO.writeData(listUsers);
                break;
            case 2:
                if (!listPatients.isEmpty()) {
                    updateAPatient();
                    userDataIO.writeData(listUsers);
                } else {
                    System.out.println(ConsoleColors.RED + "You don't have any patients yet!!!");
                }
                break;
            default:
                break;
        }

    }

    private void addNewPatient() throws IOException {
        while (true) {
            int patientid = validate.getINT_LIMIT("Enter patient id: ", 1, Integer.MAX_VALUE);
            Patient patient = validationAdminManager.getPatientByPatientID(patientid, listPatients);
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
            Patient patient = validationAdminManager.getPatientByPatientID(patientid, listPatients);
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

    private void printMENU_AddUpdatePatient() {
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Add new a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Update a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
    }

}
