package controller;

import entity.Consult;
import entity.Doctor;
import entity.Role;
import entity.User;

/**
 * This class just for authentication, contains current user
 * @author shado
 */
public abstract class BaseManager {
    protected User currentUser;
    protected String message;
    
    public BaseManager() {
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getMessage() {
        return message;
    }
    
    // ******************* Helper methods *******************
    public boolean loggedIn() {
        return currentUser != null;
    }
    protected boolean haveFullPermission(String userCode) {
        return ((loggedIn()) && ((currentUser.getRole() == Role.ADMIN) || (currentUser.getUserCode().equals(userCode))));
    }
    protected boolean adminOrInvolvedDoctor(Doctor theDoctor) {
        return ((loggedIn()) && ((currentUser.getRole() == Role.ADMIN) || (theDoctor.equals(currentUser))));
    }
}

