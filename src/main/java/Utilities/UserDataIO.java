/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import User.User;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserDataIO {

    public UserDataIO() {
    }

    public ArrayList<User> readData() {
        ObjectInputStream in = null;
        ArrayList<User> users = null;
        try {
            in = new ObjectInputStream(new FileInputStream("users.dat"));
            users = (ArrayList<User>) in.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(UserDataIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return users;
    }

    public void writeData(List<User> users) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("users.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(users);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(UserDataIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
