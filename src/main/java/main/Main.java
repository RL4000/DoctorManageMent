package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import boundary.ConsoleColors;
import boundary.Validate;
import controller.DoctorManager;
import entity.Role;
import entity.Specialization;
import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class Main {

    private static DoctorManager testUserManager = new DoctorManager();
    
    public static void main(String[] args) {
        //------------------ADD TAM DATA VAO FILE USERS.DAT DE TEST, XOA SAU
        
        testUserManager.loadUserList();
        testUserManager.addUser("adminCode", "admin", "admin", Role.ADMIN);
        testUserManager.addDoctor("doctor01", "doctor01", "doctor01", Specialization.TIM_MACH, LocalDateTime.now());
        testUserManager.addDoctor("doctor02", "doctor02", "doctor02", Specialization.UNG_BUOU, LocalDateTime.now());
        testUserManager.addDoctor(null, "doctorNull", "doctorNull", Specialization.NHA_KHOA, LocalDateTime.now());
        testUserManager.addUser("benhNhan01", "benhNhan01", "benhNhan01", Role.USER);
        testUserManager.addUser("benhNhan02", "benhNhan01", "benhNhan02", Role.USER);
        testUserManager.saveUserList();
        //------------------ADD TAM DATA VAO FILE USERS.DAT DE TEST, XOA SAU
        int userChoice;
        boolean exit = false;
        while (!exit) {
            if (!testUserManager.loggedIn()) { // Not logged in
                printLoginMenu();
                userChoice = Validate.getINT_LIMIT("Choose _", 0, 1);
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
                        functionsForAdmin();
                        break;
                    }
                    case DOCTOR: {
                        functionsForDoctor();
                        break;
                    }
                    case USER: {
                        functionsForUsers();
                        break;
                    }
                }
            }
        }

//        login();
//        mainMenu();

    }
    // ******************* Print methods *******************
    private static void printLoginMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "Welcome to Doctor Management Program");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Login");
        System.out.println(ConsoleColors.BLUE_BOLD + "0. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printAdminMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "ADMIN PANEL");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View/Add/Update/Delete Doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add/Update Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. View Doctor info incl. Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. View/Add/Update/Delete User; Change User's Password");
        System.out.println(ConsoleColors.BLUE_BOLD + "5. Query & Print out patients info");
        System.out.println(ConsoleColors.BLUE_BOLD + "6. Change password");
        System.out.println(ConsoleColors.BLUE_BOLD + "7. Logout");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }
    private static void printAdminMenu1() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Update");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Delete");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printDoctorMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "DOCTOR PANEL");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Add/Update Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. View Doctor info incl. Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Change password");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Log out");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }
    // ******************* Fucntions by role *******************
    private static void functionsForAdmin() {
        printAdminMenu();
        int choice = Validate.getINT_LIMIT("Choose _", 1, 7);
        switch (choice) {
            case 1: {
                printAdminMenu1();
                int subchoice = Validate.getINT_LIMIT("Choose _", 1, 4);
                switch (subchoice) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        break;
                    }
                    case 4: {
                        break;
                    }
                }
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                break;
            }
        }
    }
    private static void functionsForDoctor() {
        int choice = Validate.getINT_LIMIT("Choose _", 1, 7);
        switch (choice) {
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                break;
            }
        }
    }
    private static void functionsForUsers() {
        int choice = Validate.getINT_LIMIT("Choose _", 1, 7);
        switch (choice) {
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                break;
            }
        }
    }
    
    // ******************* Other functions *******************
    private static void login() {
        String userName = Validate.getString("Enter username: ");
        String password = Validate.getString("Enter password: ");
        testUserManager.login(userName, password);
        System.out.printf("%s\n", testUserManager.getMessage());
    }
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

}
