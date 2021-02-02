package controller;

import boundary.DataIO;
import boundary.Validate;
import entity.Role;
import entity.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

public class UserManager extends BaseManager {

    protected ArrayList<User> userList;
    protected DataIO<User> dataIO = new DataIO<>("users.dat");

    public UserManager() {
        this.userList = new ArrayList<>();
    }

    public UserManager(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    // ***************************************************
    //     Helper methods
    // ***************************************************
    
    /**
     * Load user list from file
     */
    public void loadUserList() {
        ArrayList<User> tempList = dataIO.readData();
        if (tempList != null) {
            userList = tempList;
        }
    }

    /**
     * Save user list to file
     */
    public void saveUserList() {
        dataIO.writeData(userList);
    }

    /**
     * Get user from user list by code, if not found return null
     *
     * @param userCode
     * @return
     */
    public User getUser(String userCode) {
        System.out.println((userList != null) ? "Not null" : "Null");
        System.out.println("User list lenght " + userList.size());
        System.out.println("Checking user code " + userCode);

        if (userList != null) {
            for (User theUser : userList) {

                if (theUser.getUserCode() != null) {
                    if (theUser.getUserCode().equals(userCode)) {
                        System.out.println("Found equal");
                        return theUser;
                    }
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

    private static Role selectRole() {
        int count = 0;
        for (Role currentRole : Role.values()) {
            count++;
            System.out.println(count + ". " + currentRole.name());
        }
        return Role.values()[Validate.getINT_LIMIT("Select role: ", 1, count) - 1];
    }

    // ***************************************************
    //     Main methods
    // ***************************************************
    public void login() {
        String userName = Validate.getString("Enter user code: ");
        String password = Validate.getString("Enter password: ");
        login(userName, password);
        System.out.printf("%s\n", getMessage());
    }
     /**
     * Check if hash-ed string match.
     *
     * @param userPassword
     * @param passwordHash
     * @param salt
     * @return Match: true | Not match: false
     */
    public static boolean verifyPassword(String userPassword, String salt, String passwordHash) {
        return hashPassword(userPassword, salt).equals(passwordHash);
    }

    /**
     * Part of login function, use to hash password.
     *
     * @param userPassword
     * @param salt
     * @return hashed password string
     */
    public static String hashPassword(String userPassword, String salt) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((userPassword + salt).getBytes());
            byte[] bytesResult = md.digest();
            result = String.format("%064x", new BigInteger(1, bytesResult));
        } catch (NoSuchAlgorithmException ex) {
        }
        return result;
    }
    
    /**
     * Add user to user list
     */
    public void addUser() {
        String userCode = Validate.getString("User code: ");
        String userName = Validate.getUsername("User name: ");
        String password = Validate.getPassword("Password: ");
        Role role = selectRole();
        addUser(userCode, userName, password, role);
        System.out.println(message);
    }

    public void addUser(Role role) {
        String userCode = Validate.getString("User code: ");
        String userName = Validate.getUsername("User name: ");
        String password = Validate.getPassword("Password: ");
        addUser(userCode, userName, password, role);
        System.out.println(message);
    }

    /**
     * Them 1 user vao user list, tra lai user vua add
     *
     * @param userCode
     * @param userName
     * @param password
     * @param role
     * @return
     */
    public void addUser(String userCode, String userName, String password, Role role) {

        if (getUser(userCode) == null) { // Add new user

            String salt = UUID.randomUUID().toString();

            String passwordHash = hashPassword(password, salt);

            User theNewUser = new User(userCode, userName, passwordHash, salt, role);

            userList.add(theNewUser);
            message = theNewUser.toString() + " added";
            saveUserList();
        } else { // User existed

            message = "There is already user code " + userCode + " in list";
        }
    }

    public void updateUser(Role role) {
        String userCode = Validate.getString("User code: ");
        User toUpdate = getUser(userCode);
        if (toUpdate.getRole() == role) {
            if (toUpdate != null) {
                if (Validate.getYesNo("Change user name? _")) {
                    toUpdate.setUserName(Validate.getUsername("User name: "));
                }
                if (Validate.getYesNo("Change user role? _")) {
                    toUpdate.setRole(selectRole());
                }
            } else {
                System.out.println("User " + userCode + " not found");
            }
        } else {
            System.out.println("You don't have permission to update this");
        }

    }

    public void updateUser() {
        String userCode = Validate.getString("User code: ");
        User toUpdate = getUser(userCode);
        if (toUpdate != null) {
            if (Validate.getYesNo("Change user name? _")) {
                toUpdate.setUserName(Validate.getUsername("User name: "));
            }
            if (Validate.getYesNo("Change user role? _")) {
                toUpdate.setRole(selectRole());
            }
        } else {
            System.out.println("User " + userCode + " not found");
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
        User userToUpdate = getUser(userCode);
        // Check if user to update exists
        if (getUser(userCode) != null) {
            // Check for permission to update
            userToUpdate.setUserName(userUpdate.getUserName());
            saveUserList();
        } else {
            message = "User " + userCode + " not found";
        }
    }

    public void deleteUser() {
        String userCode = Validate.getString("User code: ");
        deleteUser(userCode);
        saveUserList();
    }

    /**
     * Delete user
     *
     * @param userCode
     */
    public void deleteUser(String userCode) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserCode().equals(userCode)) {
                userList.remove(i);
            }
        }
        saveUserList();
    }

    public void changePassword() {
        String oldPassword = Validate.getString("Enter old password: ");
        String newPassword = Validate.getString("Enter new password: ");
        String againNewPassword = Validate.getString("Enter new password again: ");
        if (newPassword.equals(againNewPassword)) {
            changePassword(newPassword, oldPassword);
        } else {
            System.out.println("New password second time enter not match");
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
      
            if (verifyPassword(oldPasword, currentUser.getSalt(), currentUser.getPasswordHash())) { // Confirm old password
                String salt = UUID.randomUUID().toString();
                String passwordHash = hashPassword(newPassword, salt);
                currentUser.setSalt(salt);
                currentUser.setPasswordHash(passwordHash);
                saveUserList();
                message = "Password changed";
            } else {
                message = "Old password incorrect";
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
    public void login(String userName, String password) {
        for (User theUser : userList) {
            if (theUser.getUserCode() != null) {
                if (theUser.getUserCode().equals(userName) && verifyPassword(password, theUser.getSalt(), theUser.getPasswordHash())) {
                    currentUser = theUser;
                    message = "Logged in as " + userName;
                } else {
                    message = "Incorrect user name or password";
                }
            }
        }
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
            String passwordHash = hashPassword(password, salt);
            theUser.setPasswordHash(passwordHash);
            userList.add(theUser);
            message = "User code created";
            dataIO.writeData(userList);
        } else {
            message = "Can not add duplicate user code";
        }
    }

    // ***************************************************
    //     Print methods
    // ***************************************************
    
    /**
     * Print out user list. Only admin can.
     */
    public void printUserList() {
        System.out.println("User list:");
        int count = 1;
        for (User user : userList) {
            System.out.printf("%d. %s\n", count, user.toString());
            count++;
        }
        System.out.println("---------");
    }

    public void printUserList(Role theRole) {
        int count = 1;
        for (User user : userList) {
            if (user.getRole() == theRole) {
                System.out.printf("%d. %s\n", count, user.toString());
                count++;
            }
        }
    }
}
