/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
 * @param <T>
 */
public final class DataIO<T> {

    private String USER_SAVE_FILE_NAME;

    public DataIO() {
    }

    public DataIO(String USER_SAVE_FILE_NAME) {
        this.USER_SAVE_FILE_NAME = USER_SAVE_FILE_NAME;
    }

    // ******************* Main methods *******************
    /**
     * Read list of users from binary file
     *
     * @return
     */
    public ArrayList<T> readData() {
        ArrayList<T> users = new ArrayList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(USER_SAVE_FILE_NAME));
            users = (ArrayList<T>) in.readObject();
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
    public void writeData(List<T> users) {
        try {
            FileOutputStream fos = new FileOutputStream(USER_SAVE_FILE_NAME, false);
            PrintWriter writer = new PrintWriter(new File(USER_SAVE_FILE_NAME));
            writer.print("");
            writer.close();
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
