/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Common.ConsoleColors;
import Utilities.UserDataIO;
import Utilities.Validate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserController {

    public static UserController userController = null;
    private User user;
    private UserDataIO userDataIO;
    private Validate validate;

    public UserController() {
        userDataIO = new UserDataIO();
        validate = new Validate();
    }

    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController();
        }

        return userController;
    }

    //Return true if log in successfully
    //Return false if not
    public Boolean login() {
        try {
            //Doc file
            ArrayList<User> users = UserView.getInstance().getUsers();

            //Read userInput
            String userName;
            String password;

            userName = validate.getString("Input username: ");
            password = validate.getString("Input password: ");

            users.forEach((u) -> {
                if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                    user = new User();
                    user.setUserName(userName);
                    user.setPassword(password);
                    user.setUserCode(u.getUserCode());
                    user.setUserRole(u.getUserRole());
                }
            });

            return (user != null);

        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void logout() {
        this.user = null;
    }

    public void changePassword() {

        while (true) {
            try {
                System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
                System.out.println(ConsoleColors.BLUE_BOLD + "CHANGE PASSWORD");
                System.out.println(ConsoleColors.BLUE_BOLD + "1. Change Password");
                System.out.println(ConsoleColors.BLUE_BOLD + "0. Cancel");
                System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");

                int choice = validate.getINT_LIMIT("Your choice: ", 0, 1);

                switch (choice) {
                    case 0:

                        return;

                    case 1:
                        if (user != null) {

                            String oldPassword = validate.getString("Enter old password: ");
                            if (user.getPassword().equals(oldPassword)) {

                                String newPassword = validate.getPassword("Enter new password: ");
                                String confirmNewPassword = validate.getPassword("Confirm new password: ");

                                if (confirmNewPassword.equals(newPassword)) {
                                    user.setPassword(newPassword);
                                    UserView.getInstance().updateUser(user);

                                    System.out.println(ConsoleColors.GREEN_BOLD + "Password changed successfully!!");
                                } else {
                                    System.out.println(ConsoleColors.RED + "Passwords don't match!!");
                                }

                            } else {
                                System.out.println(ConsoleColors.RED + "Wrong password!!");
                            }

                        }
                        break;
                }

            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void printLoginMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "Welcome to Doctor Management Program");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Login");
        System.out.println(ConsoleColors.BLUE_BOLD + "0. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    public User getLoggedInUser() {
        return user;
    }

}
