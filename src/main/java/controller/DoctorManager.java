package controller;

import boundary.UserDataIO;
import controller.UserManager;
import static boundary.UserDataIO.readData;
import entity.Doctor;
import entity.Role;
import entity.Specialization;
import entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class DoctorManager extends UserManager {

    public DoctorManager() {
        userList = new ArrayList<>();
    }

    // ******************* Helper methods *******************
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

    // ******************* Main methods *******************
    /**
     * Add doctor to user list
     *
     * @param userCode
     * @param userName
     * @param password
     * @param specialization
     * @param avaliability
     */
    public void addDoctor(String userCode, String userName, String password, Specialization specialization, LocalDateTime avaliability) {
        if ((currentUser != null) && (currentUser.getRole() == Role.ADMIN)) {
            String salt = UUID.randomUUID().toString();
            String passwordHash = UserDataIO.hashPassword(password, salt);
            Doctor doctorAdded = new Doctor(getLastDoctorID() + 1, specialization, avaliability, userCode, userName, passwordHash, salt, Role.DOCTOR);
        }
    }
    // ******************* Print methods *******************
    
}
