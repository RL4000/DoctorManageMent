package controller;

import boundary.DataIO;
import boundary.Validate;
import entity.Consult;
import entity.Doctor;
import entity.Role;
import entity.Specialization;
import entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class ConsultManager extends BaseManager {

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

    // ******************* Main methods *******************
    /**
     * Add a consult to consult list with auto increment consult ID
     *
     * @param consultID
     * @param doctor
     * @param patient
     * @param diseaseType
     * @param consultTime
     * @param note
     */
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

    public void updateConsult(int consultID, Consult consultUpdate) {
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
            message = "Consult " + consultID + " not found";
        }
        saveConsultList();
    }

    /**
     * Delete a consult from consult list
     *
     * @param consultID
     */
    public void deleteConsult(int consultID) {
        Consult toDelete = getConsult(consultID);
        if (toDelete != null) {
            if (currentUser.getRole() == Role.ADMIN) {
                consultList.remove(toDelete);
            }
        } else {
            message = "Consult " + consultID + " not found";
        }
    }

    // ******************* Sort methods *******************
    public void sortByDiseaseType() {
        consultList.sort(new sortByDiseaseType());
    }
    // ***************************************************
    //     Print methods
    // ***************************************************

    public void printConsultLists() {
        if (consultList.size() > 0) {

            if (currentUser.getRole() == Role.ADMIN) {
                for (Consult currentConsult : consultList) {
                    System.out.printf("%s\n", currentConsult.toString());
                }
            }

        }
    }

    public void printConsultLists(Doctor doctor) {
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
}
// ******************* Sorting class *******************

class sortByDiseaseType implements Comparator<Consult> {

    @Override
    public int compare(Consult o1, Consult o2) {
        return o1.getDiseaseType().name().compareTo(o2.getDiseaseType().name());
    }

}
