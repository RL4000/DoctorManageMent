/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserView {
    ArrayList<User> users;

    public static UserView userView = null;
    
    public UserView() {
        users = new ArrayList<User>();
    }
    
    public static UserView getInstance(){
        if(userView == null){
            userView = new UserView();
        }
        
        return userView;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    public void addUser(User user){
        users.add(user);
    }
    
    
    public void deleteUser(String userCode){
        users.forEach((u) -> {
            if (u.getUserCode().equalsIgnoreCase(userCode)) {
            users.remove(u);
        }});
    }
    
    
    
    
    
    
    
}
