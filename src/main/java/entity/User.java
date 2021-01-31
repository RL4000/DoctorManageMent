package entity;

import boundary.UserDataIO;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class User implements Serializable{
    private String userCode;
    private String userName;
    private String passwordHash;
    private String salt;
    private Role role;

    public User() {
    }
    
    public User(String userCode, String userName, String passwordHash, String salt, Role role) {
        this.userCode = userCode;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.role = role;
    }
    

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        final User other = (User) obj;
        if (!Objects.equals(this.userCode, other.userCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return userCode + " | " + role.name() +" named: " + userName;
    }
    
}

