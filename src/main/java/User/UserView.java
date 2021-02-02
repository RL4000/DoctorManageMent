/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Utilities.UserDataIO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserView {

    ArrayList<User> users;
    UserDataIO userDataIO;
    public static UserView userView = null;

    public UserView() {
        users = new ArrayList<>();
        userDataIO = new UserDataIO();
    }

    public static UserView getInstance() {
        if (userView == null) {
            userView = new UserView();
        }
        return userView;
    }

    public ArrayList<User> getUsers() {
        return userDataIO.readDataUser();
    }

    public void addUser(User user) {
        users = userDataIO.readDataUser();
        users.add(user);
        userDataIO.writeDataUser(users);
    }

    public void deleteUser(String userCode) {
        users = userDataIO.readDataUser();
        users.forEach((u) -> {
            if (u.getUserCode().equalsIgnoreCase(userCode)) {
                users.remove(u);
            }
        });
        userDataIO.writeDataUser(users);
    }

    public void updateUser(User userUpdate) {
        users = userDataIO.readDataUser();
        users.forEach((u) -> {
            if (u.getUserCode().equalsIgnoreCase(userUpdate.getUserCode())) {
                u.setUserName(userUpdate.getUserName());
                u.setPassword(userUpdate.getPassword());
            }
        });
        userDataIO.writeDataUser(users);
    }

}
