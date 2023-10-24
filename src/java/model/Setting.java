/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SHD
 */
public class Setting {

    private int setting_id;
    private int is_active;
    private String name;
    private String type;

    public Setting() {
    }

    public Setting(int setting_id, int is_active, String name, String type) {
        this.setting_id = setting_id;
        this.is_active = is_active;
        this.name = name;
        this.type = type;
    }
    public Setting(int is_active, String name, String type) {
       
        this.is_active = is_active;
        this.name = name;
        this.type = type;
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
