package controller;

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

