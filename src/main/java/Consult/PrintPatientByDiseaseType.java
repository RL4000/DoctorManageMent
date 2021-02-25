package Consult;

import Common.Patient;
import Common.UserRole;
import Doctor.Doctor;
import Doctor.Specialization;
import User.User;
import java.util.ArrayList;

public class PrintPatientByDiseaseType {

    public PrintPatientByDiseaseType() {
    }
    
    /**
     * Query & print of the patients grouped by disease type
     */
    public static void printPatientByDiseaseType(ArrayList<User> tempUserList) {
        ArrayList<Consult> tempConsultList = new ArrayList<>();
        for (User user : tempUserList) {
            if (user.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                Doctor tempDoctor = (Doctor) user;
                for (Patient patient : tempDoctor.getPatients()) {
                    tempConsultList.add(new Consult(tempDoctor, patient.getName(), tempDoctor.getSpecialization()));
                }
            }
        }
        for (Specialization value : Specialization.values()) {
            for (Consult consult : tempConsultList) {
                if (consult.getDiseaseType() == value) {
                    System.out.println(consult.getPatient());
                }
            }
        }
    }
}

