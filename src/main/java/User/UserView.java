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
        users = new ArrayList<User>();
        userDataIO = new UserDataIO();
    }
    
    public static UserView getInstance(){
        if(userView == null){
            userView = new UserView();
        }
        
        return userView;
    }

    public ArrayList<User> getUsers() {
        return userDataIO.readData();
    }
    
    public void addUser(User user){
        users = userDataIO.readData();
        users.add(user);
        userDataIO.writeData(users);
    }
    
    
    public void deleteUser(String userCode){
        users = userDataIO.readData();
        users.forEach((u) -> {
            if (u.getUserCode().equalsIgnoreCase(userCode)) {
            users.remove(u);
        }});
        userDataIO.writeData(users);
    }
    
    public void updateUser(User userUpdate){
        users = userDataIO.readData();
        users.forEach((u) -> {
            if (u.getUserCode().equalsIgnoreCase(userUpdate.getUserCode())) {
            u.setUserName(userUpdate.getUserName());
            u.setPassword(userUpdate.getPassword());
        }});
        userDataIO.writeData(users);
        
    }
    
    
    
    
    
}
