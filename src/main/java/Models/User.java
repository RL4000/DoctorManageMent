/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Constants.UserRole;

/**
 *
 * @author Admin
 */
public class User {
    private String userCode; //not null, unique
    private String userName; //>=5 chars, must start with a letter
    private String password; //>=6 chars, include both letter & numbers, no other type of chars
    private UserRole userRole; //0 = ADMIN, 1 = DOCTOR
    
    public User() {
    }

    public User(String userCode, String userName, String password) {
        this.userCode = userCode;
        this.userName = userName;
        this.password = password;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    
    
    
}
