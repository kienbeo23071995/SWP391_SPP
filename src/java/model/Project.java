/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author asus
 */
public class Project {
    private int id;
    private String code;
    private String engname;
    private String viname;
    private Boolean status;
    private Group group;
    private String description;
    private User mentor;

    public Project() {
    }

    public Project(int id, String code, String engname, String viname, Boolean status, Group group, String description, User mentor) {
        this.id = id;
        this.code = code;
        this.engname = engname;
        this.viname = viname;
        this.status = status;
        this.group = group;
        this.description = description;
        this.mentor = mentor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getViname() {
        return viname;
    }

    public void setViname(String viname) {
        this.viname = viname;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getMentor() {
        return mentor;
    }

    public void setMentor(User mentor) {
        this.mentor = mentor;
    }
    
    
             
}
