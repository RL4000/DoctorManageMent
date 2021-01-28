/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Constants.UserRole;
import Models.User;

/**
 *
 * @author Admin
 */
public class UserController {
    
    public void something(){
        User u = new User();
        u.setUserRole(UserRole.ADMIN);
    }
}
