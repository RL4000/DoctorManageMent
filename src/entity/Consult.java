package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Consult implements Serializable{
    private int consultID;
    private Doctor doctor;
    private User patient;
    private Specialization diseaseType;
    private LocalDateTime consultTime;
    private String note;

    public Consult() {
    }

    public Consult(int consultID, Doctor doctor, User patient, Specialization diseaseType, LocalDateTime consultTime, String note) {
        this.consultID = consultID;
        this.doctor = doctor;
        this.patient = patient;
        this.diseaseType = diseaseType;
        this.consultTime = consultTime;
        this.note = note;
    }

    public int getConsultID() {
        return consultID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public User getPatient() {
        return patient;
    }

    public Specialization getDiseaseType() {
        return diseaseType;
    }

    public LocalDateTime getConsultTime() {
        return consultTime;
    }

    public String getNote() {
        return note;
    }

    public void setConsultID(int consultID) {
        this.consultID = consultID;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public void setDiseaseType(Specialization diseaseType) {
        this.diseaseType = diseaseType;
    }

    public void setConsultTime(LocalDateTime consultTime) {
        this.consultTime = consultTime;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}

