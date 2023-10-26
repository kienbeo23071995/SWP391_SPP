/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author kienb
 */
public class Group {
    private int id;
    private String name;
    private List<ClassUserDTO> list;

    public Group() {
    }

    public Group(int id, String name, List<ClassUserDTO> list) {
        this.id = id;
        this.name = name;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassUserDTO> getList() {
        return list;
    }

    public void setList(List<ClassUserDTO> list) {
        this.list = list;
    }
}
