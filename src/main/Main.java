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

    private static DoctorManager userManager = new DoctorManager();
    private static ConsultManager consultManager = new ConsultManager();
    private static boolean exit;

    public static void main(String[] args) {
        userManager.loadUserList();
        //------------------ADD TAM DATA VAO FILE USERS.DAT DE TEST, XOA SAU
        if (userManager.getUserList().isEmpty()) {
            userManager.addUser("adminCode", "admin", "admin", Role.ADMIN);
            userManager.addDoctor("doctor01", "doctor01", "doctor01", Specialization.TIM_MACH, LocalDateTime.now());
            userManager.addDoctor("doctor02", "doctor02", "doctor02", Specialization.UNG_BUOU, LocalDateTime.now());
            userManager.addDoctor(null, "doctorNull", "doctorNull", Specialization.NHA_KHOA, LocalDateTime.now());
            userManager.addUser("benhNhan01", "benhNhan01", "benhNhan01", Role.NORMAL_USER);
            userManager.addUser("benhNhan02", "benhNhan01", "benhNhan02", Role.NORMAL_USER);
            userManager.saveUserList();
        }
        userManager.printUserList();
        //------------------ADD TAM DATA VAO FILE USERS.DAT DE TEST, XOA SAU

        exit = false;
        while (!exit) {
            if (userManager.getCurrentUser() == null) { // Not logged in
                loginBlock();
            } else { // Logged in
                Role theRole = userManager.getCurrentUser().getRole();
                switch (theRole) {
                    case ADMIN: {
                        functionsForAdmin();
                        break;
                    }
                    case DOCTOR: {
                        functionsForDoctor();
                        break;
                    }
                    case NORMAL_USER: {
                        functionsForUsers();
                        break;
                    }
                }
            }
        }
    }

    // ***************************************************
    //     Functional modules
    // ***************************************************
    // --- By roles
    /**
     * Print menu and do functions for admin
     */
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
    /**
     * Print menu and do functions for doctor
     */
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
    /**
     * Print menu and do functions for other users (patients)
     */
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

    // --- By function block
    /**
     * Print menu and do login function
     */
    private static void loginBlock() {
        printLoginMenu();
        System.out.printf("Choose: _");
        int userChoice = BasicInput.getInt(0, 1);
        switch (userChoice) {
            case 0: {
                exit = true;
                break;
            }
            case 1: { // Login
                userManager.login();
                break;
            }
        }
    }
    /**
     * View list, add, update, delete doctor
     */
    private static void functionBlock1() {
        printBlockMenu1();
        int subChoice = Validate.getINT_LIMIT("Choose: ", 1, 4);
        switch (subChoice) {
            case 1: { // View list doctor
                userManager.printUserList(Role.DOCTOR);
                break;
            }
            case 2: { // Add doctor
                userManager.addDoctor();
                break;
            }
            case 3: { // Update doctor
                userManager.updateDoctor();
                break;
            }
            case 4: { // Delete doctor
                userManager.deleteDoctor();
                break;
            }
        }
    }
    /**
     * Add, update patient information of a specific doctor
     */
    private static void functionBlock2() {
        printBlockMenu2();
        int subChoice = Validate.getINT_LIMIT("Choose: ", 1, 4);
        switch (subChoice) {
            case 1: { // View list patient
                Doctor tempDoctor;
                if (userManager.getCurrentUser().getRole() == Role.DOCTOR) {
                    tempDoctor = (Doctor) userManager.getCurrentUser();
                } else {
                    int doctorID = Validate.getINT("Doctor ID: ");
                    tempDoctor = userManager.getDoctor(doctorID);
                }
                if (tempDoctor != null) {
                    consultManager.printPatientList(tempDoctor);
                } else {
                    System.out.println("Doctor not found");
                }

                break;
            }
            case 2: { // Add patient
                Doctor theDoctor = userManager.getDoctor(Validate.getINT("DoctorID"));
                User patient = userManager.getUser(Validate.getString("Patient ID"));
                if ((theDoctor != null) && (patient != null)) {
                    Specialization specialization = theDoctor.getSpecialization();
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
    /**
     * Query doctor information, including patient status. This is for admin.
     * They can view any doctor's patients
     */
    private static void functionBlock3() {
        int doctorCode = Validate.getINT("Doctor code to view: ");
        consultManager.printPatientList(userManager.getDoctor(doctorCode));
    }
    /**
     * Query doctor information, including patient status. This is for doctor,
     * they can only view their patient
     */
    private static void functionBlock3Doctor() {
        consultManager.printPatientList((Doctor) userManager.getCurrentUser());
    }
    /**
     * View list, add, update, delete user
     */
    private static void functionBlock4() {
        printBlockMenu4();
        int subChoice = Validate.getINT_LIMIT("Choose: ", 1, 4);
        switch (subChoice) {
            case 1: { // View user list
                userManager.printUserList();
                break;
            }
            case 2: { // Add user
                userManager.addUser();
                break;
            }
            case 3: { // Update user
                userManager.updateUser();
                break;
            }
            case 4: { // Delete user
                userManager.deleteUser();
            }
        }
    }
    /**
     * Query & print of the patients grouped by disease type
     */
    private static void functionBlock5() {
        consultManager.printUserByDiseaseType();
    }
    /**
     * log out, change password
     */
    private static void functionBlock6() {
        printBlockMenu6();
        int subChoice = Validate.getINT_LIMIT("Choose: ", 1, 2);
        switch (subChoice) {
            case 1: { // Log out
                userManager.logout();
                break;
            }
            case 2: { // Change password
                userManager.changePassword();
                break;
            }
        }
    }

    // ***************************************************
    //     Print functions
    // ***************************************************
    // --- Menus by role
    /**
     * Print menu for admin
     */
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
    /**
     * Print menu for doctor
     */
    private static void printDoctorMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "DOCTOR PANEL");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Add/Update Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. View Doctor info incl. Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Change password, log out");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }
    /**
     * Print menu for normal user (patient)
     */
    private static void printNormalUserMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "NORMAL USER PANEL");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Change password, log out");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }
    // --- Menu for function blocks
    /**
     * Login menu
     */
    private static void printLoginMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "Welcome to Doctor Management Program");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Login");
        System.out.println(ConsoleColors.BLUE_BOLD + "0. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }
    /**
     * View list, add, update, delete doctor
     */
    private static void printBlockMenu1() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View doctor list");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Update doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Delete doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }
    /**
     * Add, update patient information of a specific doctor
     */
    private static void printBlockMenu2() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Update patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Delete patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }
    /**
     * View list, add, update, delete user
     */
    private static void printBlockMenu4() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View user list");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add user");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Update user");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Delete user");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }
    /**
     * log out, change password
     */
    private static void printBlockMenu6() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Log out");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Change password");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }
}
