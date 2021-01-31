package controller;

import boundary.UserDataIO;
import entity.Role;
import entity.User;
import java.util.ArrayList;
import java.util.UUID;

public class UserManager extends BaseManager {

    protected ArrayList<User> userList;

    public UserManager() {
        this.userList = new ArrayList<>();
    }

    public UserManager(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    // ******************* Helper methods *******************
    /**
     * Load user list from file
     */
    public void loadUserList() {
        userList = UserDataIO.readData();
    }

    /**
     * Save user list to file
     */
    public void saveUserList() {
        UserDataIO.writeData(userList);
    }

    /**
     * Get user from user list by code, if not found return null
     *
     * @param userCode
     * @return
     */
    public User getUser(String userCode) {
        if (userList != null) {
            for (User theUser : userList) {
                if (theUser.getUserCode().equals(userCode)) {
                    return theUser;
                }
            }
        }
        return null;
    }

    /**
     * Get user from user list, if not found return null
     *
     * @param user
     * @return
     */
    public User getUser(User user) {
        return getUser(user.getUserCode());
    }

    /**
     * Check for unique user code, if yes return true, else return false
     *
     * @param userCode
     * @return
     */
    public boolean checkUniqueUserCode(String userCode) {
        for (User user : userList) {
            if (user.getUserCode().equals(userCode)) {
                return false;
            }
        }
        return true;
    }

    // ******************* Main methods *******************
    /**
     * Them 1 user vao user list, tra lai user vua add
     *
     * @param userCode
     * @param userName
     * @param password
     * @param role
     * @return
     */
    public User addUser(String userCode, String userName, String password, Role role) {
        loadUserList();
        if (getUser(userCode) == null) { // Add new user
            String salt = UUID.randomUUID().toString();
            String passwordHash = UserDataIO.hashPassword(password, salt);
            User theNewUser = new User(userCode, userName, passwordHash, salt, role);
            System.out.println(""+salt + passwordHash + role.name());
            userList.add(theNewUser);
            message = theNewUser.toString() + " added";
            saveUserList();
            return theNewUser;
        } else { // User existed
            message = "There is already user code " + userCode + " in list";
            return null;
        }
    }

    /**
     * Update user, currently only can change username. An user can update
     * his/her own account info. Admin can update all accounts info.
     *
     * @param userCode
     * @param userUpdate
     */
    public void updateUser(String userCode, User userUpdate) {
        loadUserList();
        User userToUpdate = getUser(userCode);
        // Check if user to update exists
        if (getUser(userCode) != null) {
            // Check for permission to update
            if (loggedIn()) {
                if (haveFullPermission(userCode)) {
                    userToUpdate.setUserName(userUpdate.getUserName());
                } else {
                    message = "You do not have permission to do this";
                }
            } else {
                message = "Log in to use this function";
            }
        } else {
            message = "User " + userCode + " not found";
        }
    }

    /**
     * Delete user
     *
     * @param userCode
     */
    public void deleteUser(String userCode) {
        if (loggedIn()) {
            if (haveFullPermission(userCode)) {
                loadUserList();
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getUserCode().equals(userCode)) {
                        userList.remove(i);
                    }
                }
                saveUserList();
            }
        }
    }

    /**
     * Change password. Need to confirm old password. One can only change
     * his/her own account password.
     *
     * @param newPassword
     * @param oldPasword
     */
    public void changePassword(String newPassword, String oldPasword) {
        if (loggedIn()) {
            if (UserDataIO.verifyPassword(oldPasword, currentUser.getSalt(), currentUser.getPasswordHash())) { // Confirm old password
                String salt = UUID.randomUUID().toString();
                String passwordHash = UserDataIO.hashPassword(newPassword, salt);
                currentUser.setSalt(salt);
                currentUser.setPasswordHash(passwordHash);
                saveUserList();
                message = "Password changed";
            } else {
                message = "Old password incorrect";
            }
        }
    }

    /**
     * Login function, if correct current user is set & return true, else
     * nothing happens & return false
     *
     * @param userName
     * @param password
     * @return
     */
    public boolean login(String userName, String password) {
        loadUserList();
        for (User theUser : userList) {
            if (theUser.getUserName().equals(userName) && UserDataIO.verifyPassword(password, theUser.getSalt(), theUser.getPasswordHash())) {
                currentUser = theUser;
                message = "Logged in as " + userName;
                return true;
            } else {
                message = "Incorrect user name or password";
            }
        }
        return false;
    }

    /**
     * Log out function
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Create user
     *
     * @param userCode
     * @param userName
     * @param password
     * @param theRole
     */
    public void createUser(String userCode, String userName, String password, Role theRole) {
        if (checkUniqueUserCode(userCode)) {
            User theUser = new User();
            theUser.setUserCode(userCode);
            theUser.setUserName(userName);
            theUser.setRole(theRole);
            String salt = UUID.randomUUID().toString();
            theUser.setSalt(salt);
            String passwordHash = UserDataIO.hashPassword(password, salt);
            theUser.setPasswordHash(passwordHash);
            userList.add(theUser);
            message = "User code created";
            UserDataIO.writeData(userList);
        } else {
            message = "Can not add duplicate user code";
        }
    }

    // ************ PRINT METHODS ************
    /**
     * Print out user list. Only admin can.
     */
    public void printUserList() {
        if (currentUser != null) {
            if (currentUser.getRole() == Role.ADMIN) {
                int count = 1;
                for (User user : userList) {
                    System.out.printf("%d. %s\n", count, user.toString());
                    count++;
                }
            } else {
                System.out.printf("Only admin can view user list\n");
            }
        } else {
            System.out.printf("Log in to use this function\n");
        }
    }
}
