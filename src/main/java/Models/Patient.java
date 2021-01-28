/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Patient {
    private int patientId;
    private String name;
    private String diseaseType;
    private Date consultDate;
    private String consultNote;

    public Patient() {
    }

    public Patient(int patientId, String name, String diseaseType, Date consultDate, String consultNote) {
        this.patientId = patientId;
        this.name = name;
        this.diseaseType = diseaseType;
        this.consultDate = consultDate;
        this.consultNote = consultNote;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public Date getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(Date consultDate) {
        this.consultDate = consultDate;
    }

    public String getConsultNote() {
        return consultNote;
    }

    public void setConsultNote(String consultNote) {
        this.consultNote = consultNote;
    }
    
    
    
}
