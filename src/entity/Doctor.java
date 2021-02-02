package entity;

import java.time.LocalDateTime;

public class Doctor extends User{
    private int doctorCode;
    private Specialization specialization;
    private LocalDateTime avaliability;

    public Doctor() {
    }

    public Doctor(int doctorCode, Specialization specialization, LocalDateTime avaliability, String userCode, String userName, String passwordHash, String salt, Role role) {
        super(userCode, userName, passwordHash, salt, role);
        this.doctorCode = doctorCode;
        this.specialization = specialization;
        this.avaliability = avaliability;
    }

    public int getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(int doctorCode) {
        this.doctorCode = doctorCode;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public LocalDateTime getAvaliability() {
        return avaliability;
    }

    public void setAvaliability(LocalDateTime avaliability) {
        this.avaliability = avaliability;
    }

    @Override
    public String toString() {
        return "Doctor{" + "doctorCode=" + doctorCode + ", specialization=" + specialization + ", avaliability=" + avaliability + '}';
    }

    
    
    
   
}

