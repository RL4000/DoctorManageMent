package Consult;


import boundary.Validate;
import entity.Consult;
import entity.Doctor;
import entity.Role;
import entity.Specialization;
import entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class ConsultManager {

    private DataIO<Consult> dataIO = new DataIO<>("consults.dat");
    private ArrayList<Consult> consultList;

    public ConsultManager() {
        this.consultList = dataIO.readData();
    }

    public ArrayList<Consult> getConsultList() {
        return consultList;
    }

    // ******************* Helper methods *******************
    /**
     * Load user list from file
     */
    public void loadConsultList() {
        consultList = dataIO.readData();
    }

    /**
     * Save user list to file
     */
    public void saveConsultList() {
        dataIO.writeData(consultList);
    }

    /**
     * Get last consult ID in consult list (for auto increment ID)
     *
     * @return
     */
    private int getLastConsultID() {
        if (consultList.size() > 0) {
            return consultList.get(consultList.size() - 1).getConsultID();
        } else {
            return -1;
        }
    }

    /**
     * Get consult from consult list
     *
     * @param consultID
     * @return
     */
    public Consult getConsult(int consultID) {
        for (Consult consult : consultList) {
            if (consult.getConsultID() == consultID) {
                return consult;
            }
        }
        return null;
    }

    public Consult getConsult(Consult consult) {
        return getConsult(consult.getConsultID());
    }

    public User getPatient(Doctor ofDoctor, String patientCode) {
        for (int i = 0; i < consultList.size(); i++) {
            Consult currentConsult = consultList.get(i);
            if (currentConsult.getDoctor().equals(ofDoctor)) {
                if (currentConsult.getPatient().getUserCode().equals(patientCode)) {
                    return currentConsult.getPatient();
                }
            }
        }
        return null;
    }

    // ***************************************************
    //     Main methods
    // ***************************************************
    /**
     * Add a consult to a doctor
     *
     * @param ofDoctor
     */
    public void addConsult(Doctor ofDoctor, User patient) {
        if ((ofDoctor != null) && (patient != null)) {
            Specialization specialization = ofDoctor.getSpecialization();
            LocalDateTime date = Validate.toLocalDateTime(Validate.getDate("Date: "));
            String note = Validate.getString("Note: ");
            addConsult(ofDoctor, patient, specialization, date, note);
        } else {
            System.out.println("Patient not found");
        }
    }

    public void addConsult(Doctor doctor, User patient, Specialization diseaseType, LocalDateTime consultTime, String note) {
        consultList.add(
                new Consult(
                        getLastConsultID() + 1,
                        doctor, patient, diseaseType, consultTime, note));
        saveConsultList();
    }

    public void updateConsult() {
        Consult toUpdate = getConsult(Validate.getINT("Consult ID: "));
        if (toUpdate != null) {
            toUpdate.setConsultTime(Validate.toLocalDateTime(Validate.getDate("Consult time: ")));
            toUpdate.setDiseaseType(Specialization.TIEU_HOA);
        }
        saveConsultList();
    }

    public void updateConsult(User currentUser, int consultID, Consult consultUpdate) {
        Consult toUpdate = getConsult(consultID);
        if (toUpdate != null) {
            toUpdate.setConsultTime(consultUpdate.getConsultTime());
            toUpdate.setDiseaseType(consultUpdate.getDiseaseType());
            if (currentUser.getRole() == Role.ADMIN) {
                toUpdate.setDoctor(consultUpdate.getDoctor());
                toUpdate.setPatient(consultUpdate.getPatient());
            }
            toUpdate.setNote(consultUpdate.getNote());
        } else {
            System.out.println("Consult " + consultID + " not found");
        }
        saveConsultList();
    }

    /**
     * Delete a consult from consult list
     *
     * @param consultID
     */
    public void deleteConsult(User currentUser, int consultID) {
        Consult toDelete = getConsult(consultID);
        if (toDelete != null) {
            if (currentUser.getRole() == Role.ADMIN) {
                consultList.remove(toDelete);
            }
        } else {
            System.out.println("Consult " + consultID + " not found");
        }
    }

    /**
     * Delete all consults with a doctor
     *
     * @param doctor
     */
    public void deleteConsult(Doctor doctor) {
        for (Consult currentConsult : consultList) {
            if (currentConsult.getDoctor().equals(doctor)) {
                consultList.remove(currentConsult);
            }
        }
    }

    public void deleteConsult(User patient) {
        for (Consult currentConsult : consultList) {
            if (currentConsult.getPatient().equals(patient)) {
                consultList.remove(currentConsult);
            }
        }
    }

    public void deleteConsult(Doctor doctor, User patient) {
        int count = 0;
        for (int i = 0; i < consultList.size(); i++) {
            if (consultList.get(i).getDoctor().equals(doctor)) {
                if (consultList.get(i).getPatient().equals(patient)) {
                    consultList.remove(i);
                    count++;
                }
            }
        }
        System.out.println("Removed " + count + " records");
    }

    public void deletePatient(Doctor ofDoctor) {
        User patient = getPatient(ofDoctor, Validate.getString("Patient code: "));
        if (patient != null) {
            deleteConsult(ofDoctor, patient);
        } else {
            System.out.println("Patient not found");
        }
    }

    // ***************************************************
    //     Print methods
    // ***************************************************
    public void printConsultLists(User currentUser) {
        if (consultList.size() > 0) {

            if (currentUser.getRole() == Role.ADMIN) {
                for (Consult currentConsult : consultList) {
                    System.out.printf("%s\n", currentConsult.toString());
                }
            }

        }
    }

    public void printConsultLists(User currentUser, Doctor doctor) {
        if (consultList.size() > 0) {

            if (currentUser.getRole() == Role.ADMIN) {
                for (Consult currentConsult : consultList) {
                    if (currentConsult.getDoctor().equals(doctor)) {
                        System.out.printf("%s\n", currentConsult.toString());
                    }
                }
            }

        }
    }

    public void printPatientList(Doctor doctor) {
        System.out.println(doctor.toString());
        if (consultList.size() > 0) {
            for (Consult currentConsult : consultList) {
                if (currentConsult.getDoctor().equals(doctor)) {
                    System.out.printf("%s\n", currentConsult.getPatient().toString());
                }
            }
        }
    }

    public void printUserByDiseaseType() {
        if (consultList.size() > 0) {

            Specialization currentSpecialization = consultList.get(0).getDiseaseType();
            System.out.printf("%s\n", currentSpecialization.name());
            for (Consult currentConsult : consultList) {
                if (currentSpecialization != currentConsult.getDiseaseType()) {
                    currentSpecialization = currentConsult.getDiseaseType();
                    System.out.printf("%s\n", currentSpecialization.name());
                }
                System.out.printf("\t%s\n", currentConsult.getPatient().toString());
            }

        }
    }

    // ***************************************************
    //     Sort methods
    // ***************************************************
    public void sortByDiseaseType() {
        consultList.sort(new sortByDiseaseType());
    }
}
// ******************* Sorting class *******************

class sortByDiseaseType implements Comparator<Consult> {

    @Override
    public int compare(Consult o1, Consult o2) {
        return o1.getDiseaseType().name().compareTo(o2.getDiseaseType().name());
    }

}
