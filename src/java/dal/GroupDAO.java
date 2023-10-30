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

    public void deleteByStudentAndGroup(int studentID, int groupID) {
        String sql = "update class_user  set `group` = null,isLeader = null where user_id = ? and `group` = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, studentID);
            st.setInt(2, groupID);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudentToGroup(int studentID, int oldgroup, int newgroup) {
        String sql = "update class_user  set `group` = ?,isLeader = null where user_id = ? and `group` = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newgroup);
            st.setInt(2, studentID);
            st.setInt(3, oldgroup);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addStudentToGroup(String[] ids, int groupID) {
        String sql = "update class_user  set `group` = ? where user_id = ?";
        try {
            for (String item : ids) {
                Connection connection = getJDBCConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(2, groupID);
                ps.setInt(1, Integer.parseInt(item));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
        }
    }
}
