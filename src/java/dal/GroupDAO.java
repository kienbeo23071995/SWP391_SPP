/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Class;
import model.ClassUserDTO;
import model.Group;
import model.Subject;
import ulti.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kienb
 */
public class GroupDAO extends BaseDAO {

    public List<Group> getAll(String search) {
        List<Group> list = new ArrayList<>();
        String sql = "select * from `group`";
        if (search != null && !search.trim().isEmpty()) {
            sql += " where name like ?";
        }
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            if (search != null && !search.trim().isEmpty()) {
                 st.setString(1, "%" + search + "%");
            }       
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt(1));
                group.setName(rs.getString(2));
                group.setList(getAllByGroup(rs.getInt(1) + ""));
                list.add(group);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Group getGroupByID(int id) {
        String sql = "select * from `group` where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt(1));
                group.setName(rs.getString(2));
                group.setList(getAllByGroup(rs.getInt(1) + ""));
                return group;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ClassUserDTO> getAllByGroup(String groupID) {
        List<ClassUserDTO> list = new ArrayList<>();
        String sql = "select * from `group`";
        if (groupID != null && !groupID.trim().isEmpty()) {
            sql += " where `group` = ?";
        }
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(groupID));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClassUserDTO classUserDTO = new ClassUserDTO();
                classUserDTO.setId(rs.getInt(1));
                UserDAO udo = new UserDAO();
                classUserDTO.setUser(udo.getUserById(rs.getInt(3)));
                ClassDAO classDAO = new ClassDAO();
                classUserDTO.setCls(classDAO.getClassByID(rs.getInt(2)));
                classUserDTO.setLeader(rs.getBoolean(4));
                list.add(classUserDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
