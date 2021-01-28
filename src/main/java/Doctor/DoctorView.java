/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor;

import Common.UserRole;
import Common.User;

/**
 *
 * @author Admin
 */
public class DoctorView {
    
    public void something(){
        User u = new User();
        u.setUserRole(UserRole.ADMIN);
    }
}
