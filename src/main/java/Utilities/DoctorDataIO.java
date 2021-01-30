/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Doctor.Doctor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DoctorDataIO {
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
