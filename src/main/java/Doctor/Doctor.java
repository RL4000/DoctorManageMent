package Doctor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Common.User;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Doctor extends User{
    private int doctorId;
    private String specialization;
    private Date availability; //

    public Doctor() {
    }

    public Doctor(String userCode, String userName, String password, int doctorId, String specialization, Date availability) {
        super(userCode, userName, password);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.availability = availability;
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
    
    
}
