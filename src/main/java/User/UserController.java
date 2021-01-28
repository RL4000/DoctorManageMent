/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Admin.Admin;
import Common.ConsoleColors;
import Utilities.UserDataIO;
import Utilities.Validate;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.List;
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
    
    public static UserController getInstance(){
        if(userController == null){
            userController = new UserController();
        }
        
        return userController;
    }
    
    //Return true if log in successfully
    //Return false if not
    public Boolean login(){
        try {
            //Doc file
            ArrayList<User> users = userDataIO.readData();
            
            //Read userInput
            String userName;
            String password;
            
            userName = validate.getString("Input username: ");
            password = validate.getString("Input password: ");
            
            user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            
            users.forEach((u) -> {
                if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
                    user.setIsLoggedIn(Boolean.TRUE);
                    user.setUserRole(u.getUserRole());
                }     
            });
            
            return user.getIsLoggedIn();
            
            
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
    public void logout(){
        this.user = null;
    }
    
    
    
    private static void printLoginMenu(){
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
