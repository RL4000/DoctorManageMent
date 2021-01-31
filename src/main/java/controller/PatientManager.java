package controller;

import entity.User;
import java.util.ArrayList;

public class PatientManager extends UserManager{

    public PatientManager() {
    }

    public PatientManager(ArrayList<User> userList) {
        super(userList);
    }
    
    
}

