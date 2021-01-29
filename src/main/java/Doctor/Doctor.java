package Doctor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Common.Patient;
import Common.UserRole;
import User.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Doctor extends User implements Serializable {

    private int doctorId;
    private String specialization;
    private Date availability; //
    private ArrayList<Patient> patients;

    public Doctor() {
    }
    
    

    public Doctor(String userName, String password, UserRole userRole) {
        super(userName, password, userRole);
    }

    public Doctor(String userCode, String userName, String password, UserRole userRole) {
        super(userCode, userName, password, userRole);
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Date getAvailability() {
        return availability;
    }

    public void setAvailability(Date availability) {
        this.availability = availability;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    

}
