/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.BaseDAO.getJDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClassIssueSetting;
import ulti.Helper;

/**
 *
 * @
 */
public class ClassSettingDAO extends BaseDAO {

    public List<ClassIssueSetting> getAll(String searchName, String status, int classID) {
        List<ClassIssueSetting> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String sql = "Select * from class_issue_setting where classID = ?";
        params.add(classID);
        try {
            if (searchName != null && !searchName.trim().isEmpty()) {
                sql += " and (type like ? or workprocess like ?) ";
                params.add(searchName);
                params.add(searchName);
            }
            if (status != null && !status.trim().isEmpty()) {
                sql += " and statuses = ?";
                params.add(status.equals("1"));
            }
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            Helper.setParams(st, params);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ClassIssueSetting cis = new ClassIssueSetting();
                cis.setId(rs.getInt(1));
                cis.setType(rs.getString(2));
                cis.setWorkprocess(rs.getString(4));
                cis.setStatus(rs.getBoolean(3));
                cis.setCls(new ClassDAO().getClassByID(rs.getInt(5)));
                list.add(cis);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteByID(int id) {
        String sql = "delete\n"
                + "from class_issue_setting\n"
                + "where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(String name, String status, String process) {
        String sql = "insert into class_setting ( status, workprocess, name)\n"
                + "values (?,?,?)";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(3, status);
            st.setString(2, process);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassIssueSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(String id, String name, Boolean status, String process, int classID) {
        String sql = "update class_issue_setting\n"
                + "set type = ?,\n"
                + "    workprocess = ?,\n"
                + "    statuses = ?,\n"
                + "    classID = ?\n"
                + "where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, process);
            st.setBoolean(3, status);
            st.setInt(4, classID);
            st.setInt(5, Integer.parseInt(id));
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassIssueSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(String type, boolean equals, String workprocess, int classID) {
        String sql = "insert into class_issue_setting (type, statuses, workprocess, classID)\n"
                + "values (?,?,?,?)";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, type);
            st.setBoolean(2, equals);
            st.setString(3, workprocess);
            st.setInt(4, classID);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassIssueSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
