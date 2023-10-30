/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class RoleName {
    private int rid;
    private String rname;
    
    public RoleName() {
    }

    public RoleName(int rid, String rname) {
        this.rid = rid;
        this.rname = rname;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Override
    public String toString() {
        return "RoleName{" + "rid=" + rid + ", rname=" + rname + '}';
    }
    
}
