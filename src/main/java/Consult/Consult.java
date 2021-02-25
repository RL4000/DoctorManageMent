package Consult;

import Doctor.Specialization;
import Doctor.Doctor;
import User.User;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Consult implements Serializable {

    private Doctor doctor;
    private String patient;
    private Specialization diseaseType;
    private LocalDateTime consultTime;
    private String note;

    public Consult() {
    }

    public Consult(Doctor doctor, String patient, Specialization diseaseType) {
        this.doctor = doctor;
        this.patient = patient;
        this.diseaseType = diseaseType;
    }
    
    

    public Consult(Doctor doctor, String patient, Specialization diseaseType, LocalDateTime consultTime, String note) {
        this.doctor = doctor;
        this.patient = patient;
        this.diseaseType = diseaseType;
        this.consultTime = consultTime;
        this.note = note;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Specialization getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(Specialization diseaseType) {
        this.diseaseType = diseaseType;
    }

    public LocalDateTime getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(LocalDateTime consultTime) {
        this.consultTime = consultTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Consult{" + "doctor=" + doctor + ", patient=" + patient + ", diseaseType=" + diseaseType + ", consultTime=" + consultTime + ", note=" + note + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consult other = (Consult) obj;
        if (!Objects.equals(this.patient, other.patient)) {
            return false;
        }
        if (!Objects.equals(this.doctor, other.doctor)) {
            return false;
        }
        if (this.diseaseType != other.diseaseType) {
            return false;
        }
        if (!Objects.equals(this.consultTime, other.consultTime)) {
            return false;
        }
        return true;
    }

}
