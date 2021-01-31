/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entity.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public final class UserDataIO {

    private static final String USER_SAVE_FILE_NAME = "users.dat";

    // ******************* Main methods *******************
    /**
     * Read list of users from binary file
     *
     * @return
     */
    public static ArrayList<User> readData() {
        ArrayList<User> users = new ArrayList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(USER_SAVE_FILE_NAME));
            users = (ArrayList<User>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    /**
     * Write list of users to file
     *
     * @param users
     */
    public static void writeData(List<User> users) {
        try {
            FileOutputStream fos = new FileOutputStream(USER_SAVE_FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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
}
