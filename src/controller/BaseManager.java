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
}

