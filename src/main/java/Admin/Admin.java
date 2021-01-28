/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Common.UserRole;
import User.User;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Admin extends User implements Serializable{

    public Admin() {
    }

    public Admin(String userName, String password, UserRole userRole) {
        super(userName, password, userRole);
    }

    public Admin(String userCode, String userName, String password, UserRole userRole) {
        super(userCode, userName, password, userRole);
    }
    
    
    
}
