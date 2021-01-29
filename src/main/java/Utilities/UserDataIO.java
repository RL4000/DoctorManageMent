/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import User.User;
import Doctor.Doctor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserDataIO {

    public UserDataIO() {
    }

    public ArrayList<User> readDataUser() {
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.dat"))) {
                return (ArrayList<User>) in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeDataUser(ArrayList<User> users) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
                oos.writeObject(users);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Doctor> readDataDoctor() {
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("doctor.dat"))) {
                return (ArrayList<Doctor>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeDateDoctor(ArrayList<Doctor> doctors) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("doctor.dat"))) {
                oos.writeObject(doctors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Doctor getDoctorByUserCode(String userCode){
        for (Doctor doctor : readDataDoctor()) {
            if (doctor.getUserCode().equals(userCode)) {
                return doctor;
            }
        }
        return null;
    }
}
