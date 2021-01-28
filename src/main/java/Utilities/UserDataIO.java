/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import User.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserDataIO {

    public UserDataIO() {
    }

    public ArrayList<User> readData() {
        ArrayList<User> users = null;
    try {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.dat"));
        users = (ArrayList<User>) in.readObject(); 
        in.close();
    }
    catch(Exception e) {
        System.out.println(e.getMessage());
    }

        return users;
    }

    public void writeData(List<User> users) {
        try {
            FileOutputStream fos = new FileOutputStream("users.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
