/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kienb
 */
public class UserDTO {
    private User user;
    private Boolean isLeader;
    private model.Class cls;

    public UserDTO() {
    }

    public UserDTO(User user, Boolean isLeader, Class cls) {
        this.user = user;
        this.isLeader = isLeader;
        this.cls = cls;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Boolean isLeader) {
        this.isLeader = isLeader;
    }
    
    
}
