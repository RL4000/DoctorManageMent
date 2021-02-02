package controller;

import boundary.UserDataIO;
import controller.UserManager;
import static boundary.UserDataIO.readData;
import boundary.Validate;
import entity.Doctor;
import entity.Role;
import entity.Specialization;
import entity.User;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;

public class DoctorManager extends UserManager {

    public DoctorManager() {

    }

    // ***************************************************
    //     Helper methods
    // ***************************************************
    /**
     * Return last doctor ID in array list
     *
     * @return
     */
    private int getLastDoctorID() {
        for (int i = userList.size() - 1; i >= 0; i--) {
            User tempUser = userList.get(i);
            if (tempUser.getRole() == Role.DOCTOR) {
                return ((Doctor) tempUser).getDoctorCode();
            }
        }
        return -1;
    }

    public Doctor getDoctor(int doctorID) {
        for (User user : userList) {
            if (user instanceof Doctor) {
                if (((Doctor) user).getDoctorCode() == doctorID) {
                    return (Doctor) user;
                }
            }
        }
        return null;
    }

    public Specialization selectSpecialization() {
        int count = 0;
        for (Specialization currentSpecialization : Specialization.values()) {
            count++;
            System.out.println(count + ". " + currentSpecialization.name());
        }
        return Specialization.values()[Validate.getINT_LIMIT("Select specialization: ", 1, count) - 1];
    }

    // ***************************************************
    //     Main methods
    // ***************************************************
    /**
     * Add doctor
     */
    public void addDoctor() {
        String userCode = Validate.getString("User code: ");
        String userName = Validate.getString("User name: ");
        String password = Validate.getString("Password: ");
        Specialization specialization = selectSpecialization();
        LocalDateTime avaliability = LocalDateTime.ofInstant(Validate.getDate_LimitToCurrent("Avaliability: ").toInstant(), ZoneId.systemDefault());
        addDoctor(userCode, userName, password, specialization, avaliability);
    }

    public void addDoctor(String userCode, String userName, String password, Specialization specialization, LocalDateTime avaliability) {
        String salt = UUID.randomUUID().toString();
        String passwordHash = UserDataIO.hashPassword(password, salt);
        Doctor doctorAdded = new Doctor(getLastDoctorID() + 1, specialization, avaliability, userCode, userName, passwordHash, salt, Role.DOCTOR);
        userList.add(doctorAdded);
        System.out.printf("%s\n", doctorAdded.toString() + " added");
        saveUserList();
    }

    public void updateDoctor() {
        int doctorID = Validate.getINT("Doctor ID: ");
        updateDoctor(doctorID);
    }

    public void updateDoctor(int doctorID) {
        Doctor toUpdate = getDoctor(doctorID);
        if (toUpdate != null) {
            if (Validate.getYesNo("Update doctor name? _")) {
                toUpdate.setUserName(Validate.getUsername("Doctor name: "));
            }
            if (Validate.getYesNo("Update doctor's user code? _")) {
                toUpdate.setUserCode("User code: ");
            }
            if (Validate.getYesNo("Update specialization? _")) {
                toUpdate.setSpecialization(selectSpecialization());
            }
            if (Validate.getYesNo("Update avaliability? _")) {
                toUpdate.setAvaliability(Validate.toLocalDateTime(Validate.getDate_LimitFromCurrent("Avaliability: ")));
            }
        } else {
            System.out.println("Doctor not exist");
        }
    }

    public void updateDoctor(int doctorID, Doctor updateDoctor) {
        Doctor toUpdate = getDoctor(doctorID);
        if (toUpdate != null) {
            toUpdate.setUserName(updateDoctor.getUserName());
            toUpdate.setUserCode(updateDoctor.getUserCode());
            toUpdate.setSpecialization(updateDoctor.getSpecialization());
            toUpdate.setAvaliability(updateDoctor.getAvaliability());
        }
    }

    public void deleteDoctor() {
        deleteDoctor(Validate.getINT("Doctor ID: "));
    }

    public void deleteDoctor(int doctorID) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getRole() == Role.DOCTOR) {
                if (((Doctor) (userList.get(i))).getDoctorCode() == doctorID) {
                    userList.remove(i);
                    break;
                }
            }
        }
    }

    // ***************************************************
    //     Print methods
    // ***************************************************
}
