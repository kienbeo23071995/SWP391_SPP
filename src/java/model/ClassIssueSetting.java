/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @
 */
public class ClassIssueSetting {
    private int id;
    private Boolean status;
    private String type;
    private String workprocess;
    private Class cls;

    public ClassIssueSetting() {
    }

    public ClassIssueSetting(int id, Boolean status, String type, String workprocess, Class cls) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.workprocess = workprocess;
        this.cls = cls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkprocess() {
        return workprocess;
    }

    public void setWorkprocess(String workprocess) {
        this.workprocess = workprocess;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    
    
    
}
