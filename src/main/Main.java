package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import boundary.BasicInput;
import boundary.ConsoleColors;
import boundary.DataIO;
import boundary.Validate;
import controller.ConsultManager;
import controller.DoctorManager;
import entity.Doctor;
import entity.Role;
import entity.Specialization;
import entity.User;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class Main {

    private static DoctorManager testUserManager = new DoctorManager();
    private static ConsultManager consultManager = new ConsultManager();
    private static boolean exit;

    public static void main(String[] args) {
        //------------------ADD TAM DATA VAO FILE USERS.DAT DE TEST, XOA SAU

        testUserManager.loadUserList();
        testUserManager.addUser("adminCode", "admin", "admin", Role.ADMIN);
        testUserManager.addDoctor("doctor01", "doctor01", "doctor01", Specialization.TIM_MACH, LocalDateTime.now());
        testUserManager.addDoctor("doctor02", "doctor02", "doctor02", Specialization.UNG_BUOU, LocalDateTime.now());
        testUserManager.addDoctor(null, "doctorNull", "doctorNull", Specialization.NHA_KHOA, LocalDateTime.now());
        testUserManager.addUser("benhNhan01", "benhNhan01", "benhNhan01", Role.NORMAL_USER);
        testUserManager.addUser("benhNhan02", "benhNhan01", "benhNhan02", Role.NORMAL_USER);
        testUserManager.saveUserList();
        //------------------ADD TAM DATA VAO FILE USERS.DAT DE TEST, XOA SAU
        testUserManager.printUserList();

        int userChoice;
        exit = false;
        while (!exit) {
            if (testUserManager.getCurrentUser() == null) { // Not logged in
                printLoginMenu();
                System.out.printf("Choose: _");
                userChoice = BasicInput.getInt(0, 1);
                switch (userChoice) {
                    case 0: {
                        exit = true;
                        break;
                    }
                    case 1: { // Login
                        login();
                        break;
                    }
                }
            } else { // Logged in
                Role theRole = testUserManager.getCurrentUser().getRole();
                switch (theRole) {
                    case ADMIN: {
                        // ******************* Admin functions *******************
                        functionsForAdmin();
                        break;
                    }
                    case DOCTOR: {
                        // ******************* Doctor functions *******************
                        // Add, update patient information of a specific doctor
                        // Query doctor information, including patient status
                        // Log in, log out, change password
                        functionsForDoctor();
                        break;
                    }
                    case NORMAL_USER: {
                        // ******************* Normal user functions *******************
                        // View list, add, update, delete doctor
                        // Add, update patient information of a specific doctor
                        // Query doctor information, including patient status
                        // View list, add, update, delete user
                        // Query & print of the patients grouped by disease type
                        // Log in, log out, change password
                        functionsForUsers();
                        break;
                    }
                }
            }
        }
    }

    // ***************************************************
    //     Functional methods
    // ***************************************************
    // BY ROLE
    private static void functionsForAdmin() {
        printAdminMenu();
        System.out.printf("Choose: _");
        int choice = BasicInput.getInt(1, 7);
        switch (choice) {
            case 1: { // View list, add, update, delete doctor
                functionBlock1();
                break;
            }
            case 2: { // Add, update patient information of a specific doctor
                functionBlock2();
                break;
            }
            case 3: { // Query doctor information, including patient status
                functionBlock3();
                break;
            }
            case 4: { // View list, add, update, delete user
                functionBlock4();
                break;
            }
            case 5: { // Query & print of the patients grouped by disease type
                functionBlock5();
                break;
            }
            case 6: { // Log in, log out, change password
                functionBlock6();
                break;
            }
            case 7: { // Exit
                exit = true;
                break;
            }
        }
    }

    private static void functionsForDoctor() {
        printDoctorMenu();
        System.out.printf("Choose: _");
        int choice = BasicInput.getInt(1, 7);
        switch (choice) {
            case 1: { // Add, update patient information of a specific doctor
                functionBlock2();
                break;
            }
            case 2: { // Query doctor information, including patient status
                functionBlock3Doctor();
                break;
            }
            case 3: { // Log in, log out, change password
                functionBlock6();
                break;
            }
            case 4: { // Exit
                exit = true;
                break;
            }
        }
    }

    private static void functionsForUsers() {
        printNormalUserMenu();
        System.out.printf("Choose: _");
        int choice = BasicInput.getInt(1, 7);
        switch (choice) {
            case 1: { // Log in, log out, change password
                functionBlock6();
                break;
            }
            case 2: { // Exit
                exit = true;
                break;
            }
        }
    }

    // BY BLOCKS
    // View list, add, update, delete doctor
    private static void functionBlock1() {
        printBlockMenu1();
        int subChoice = Validate.getINT_LIMIT("Choose: ", 1, 4);
        switch (subChoice) {
            case 1: { // View list doctor
                testUserManager.printUserList(Role.DOCTOR);
                break;
            }
            case 2: { // Add doctor
                testUserManager.addDoctor();
                break;
            }
            case 3: { // Update doctor
                testUserManager.updateDoctor();
                break;
            }
            case 4: { // Delete doctor
                testUserManager.deleteDoctor();
                break;
            }
        }
    }

    // Add, update patient information of a specific doctor
    private static void functionBlock2() {
        printBlockMenu2();
        int subChoice = Validate.getINT_LIMIT("Choose: ", 1, 4);
        switch (subChoice) {
            case 1: { // View list patient
                Doctor tempDoctor;
                if (testUserManager.getCurrentUser().getRole() == Role.DOCTOR) {
                    tempDoctor = (Doctor) testUserManager.getCurrentUser();
                } else {
                    int doctorID = Validate.getINT("Doctor ID: ");
                    tempDoctor = testUserManager.getDoctor(doctorID);
                }
                if (tempDoctor != null) {
                    consultManager.printPatientList(tempDoctor);
                } else {
                    System.out.println("Doctor not found");
                }

                break;
            }
            case 2: { // Add patient
                Doctor theDoctor = testUserManager.getDoctor(Validate.getINT("DoctorID"));
                User patient = testUserManager.getUser(Validate.getString("Patient ID"));
                if ((theDoctor != null) && (patient != null)) {
                    Specialization specialization = testUserManager.selectSpecialization();
                    LocalDateTime date = Validate.toLocalDateTime(Validate.getDate("Date: "));
                    String note = Validate.getString("Note: ");
                    consultManager.addConsult(theDoctor, patient, specialization, date, note);
                }
                break;
            }
            case 3: { // Update patient

                break;
            }
            case 4: { // Delete patient
                consultManager.deleteConsult(Validate.getINT("Consult ID:"));
                break;
            }
        }
    }

    // Query doctor information, including patient status
    private static void functionBlock3() {
        int doctorCode = Validate.getINT("Doctor code to view: ");
        consultManager.printPatientList(testUserManager.getDoctor(doctorCode));
    }

    private static void functionBlock3Doctor() {
        consultManager.printPatientList((Doctor) testUserManager.getCurrentUser());
    }

    // View list, add, update, delete user
    private static void functionBlock4() {
        printBlockMenu4();
        int subChoice = Validate.getINT_LIMIT("Choose: ", 1, 4);
        switch (subChoice) {
            case 1: { // View user list
                testUserManager.printUserList();
                break;
            }
            case 2: { // Add user
                testUserManager.addUser();
                break;
            }
            case 3: { // Update user
                testUserManager.updateUser();
                break;
            }
            case 4: { // Delete user
                testUserManager.deleteUser();
            }
        }
    }

    // Query & print of the patients grouped by disease type
    private static void functionBlock5() {
        consultManager.printUserByDiseaseType();
    }

    // log out, change password
    private static void functionBlock6() {
        printBlockMenu6();
        int subChoice = Validate.getINT_LIMIT("Choose: ", 1, 2);
        switch (subChoice) {
            case 1: { // Log out
                testUserManager.logout();
                break;
            }
            case 2: { // Change password
                testUserManager.changePassword();
                break;
            }
        }
    }

    // ***************************************************
    //     Print functions
    // ***************************************************
    // Menu by roles
    private static void printAdminMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "ADMIN PANEL");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View/Add/Update/Delete Doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add/Update Patient info of a doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Query doctor information, including patient status");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. View list, add, update, delete user");
        System.out.println(ConsoleColors.BLUE_BOLD + "5. Query & print of the patients grouped by disease type");
        System.out.println(ConsoleColors.BLUE_BOLD + "6. Log in, log out, change password");
        System.out.println(ConsoleColors.BLUE_BOLD + "7. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printDoctorMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "DOCTOR PANEL");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Add/Update Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. View Doctor info incl. Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Change password, log out");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printNormalUserMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "NORMAL USER PANEL");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Change password, log out");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    // FUNCTION BLOCK MENUS
    private static void printLoginMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "Welcome to Doctor Management Program");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Login");
        System.out.println(ConsoleColors.BLUE_BOLD + "0. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printBlockMenu1() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View doctor list");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Update doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Delete doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printBlockMenu2() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Update patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Delete patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printBlockMenu4() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View user list");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add user");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Update user");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Delete user");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printBlockMenu6() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Log out");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Change password");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    // ***************************************************
    //     Other functions
    // ***************************************************
    private static void login() {
        String userName = Validate.getString("Enter user code: ");
        String password = Validate.getString("Enter password: ");
        testUserManager.login(userName, password);
        System.out.printf("%s\n", testUserManager.getMessage());
    }
//<editor-fold defaultstate="collapsed" desc="draft">

//
//    private static void loginMenu() {
//        int choice;
//        while (true) {
//            try {
//                printLoginMenu();
//                choice = Validate.getINT_LIMIT("Your choice: ", 0, 1);
//                Boolean isLoggedIn = false;
//                switch (choice) {
//                    case 0:
//                        return;
//                    case 1:
//                        isLoggedIn = userController.login();
//                        break;
//                    default:
//                }
//
//                if (isLoggedIn) {
//                    System.out.println(ConsoleColors.PURPLE_BOLD + "LOGGED IN SUCCESSFULLY!!");
//                    break;
//                } else {
//                    System.out.println(ConsoleColors.RED_BOLD + "LOGGED IN FAILED!!");
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    private static void mainMenu() {
//        User user = userController.getLoggedInUser();
//
//        if (user != null) {
//            if (user.getRole() == Role.ADMIN) {
//                adminMenu();
//            } else if (user.getRole() == Role.AUTHORIZED_DOCTOR) {
//                doctorMenu();
//            }
//        }
//    }
//
//    private static void adminMenu() {
//        int choice = -1;
//        while (true) {
//            try {
//                printAdminMenu();
//                choice = validate.getINT_LIMIT("Your choice: ", 1, 7);
//
//                switch (choice) {
//                    case 1:
//
//                        break;
//                    case 2:
//
//                        break;
//                    case 3:
//
//                        break;
//                    case 4:
//
//                        break;
//                    case 5:
//
//                        break;
//                    case 6:
//                        userController.changePassword();
//                        break;
//                    case 7:
//                        userController.logout();
//                        login();
//                        mainMenu();
//                        return;
//                    default:
//                        break;
//
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
//
//    private static void doctorMenu() {
//        int choice = -1;
//        while (true) {
//            try {
//                printDoctorMenu();
//                choice = validate.getINT_LIMIT("Your choice: ", 1, 4);
//
//                switch (choice) {
//                    case 1:
//
//                        break;
//                    case 2:
//
//                        break;
//                    case 3:
//                        userController.changePassword();
//                        break;
//                    case 4:
//                        userController.logout();
//                        login();
//                        mainMenu();
//                        return;
//                    default:
//                        break;
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//</editor-fold>
}
