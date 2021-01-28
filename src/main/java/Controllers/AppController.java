/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Constants.ConsoleColors;

/**
 *
 * @author Admin
 */
public class AppController {

    private static AppController instance = null;
    
    public AppController() {
    } 
  
    public static AppController getInstance() 
    { 
        if (instance == null) 
            instance = new AppController(); 
  
        return instance; 
    }
    
    
    public void printMenu(){
        System.out.print(ConsoleColors.GREEN_BOLD+"");
        System.out.println("1. View list, add, update, delete doctor");
        System.out.println("2. Add, update patient information of a specific doctor");
        System.out.println("3. Query doctor information, including the patient status");
        System.out.println("4. View list, add, update, delete user; change user's password");
        System.out.println("5. Query & print out patients grouped by disease types");
        System.out.println("6. Login, Logout, change user's password");
        System.out.print(ConsoleColors.RESET+"");
    }
    
}
