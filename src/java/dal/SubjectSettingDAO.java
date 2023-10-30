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
import model.SubjectSetting;
import ulti.Helper;

/**
 *
 * @
 */
public class SubjectSettingDAO extends BaseDAO {

    public void updateSubjectSetting(int id, String type, String level, boolean status, int order) {
        String sql = "update subjectsetting\n"
                + "set name = ?,\n"
                + "    type = ?,\n"
                + "    status = ?,\n"
                + "    `order` = ?\n"
                + "    where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, level);
            st.setString(2, type);
            st.setBoolean(3, status);
            st.setInt(4, order);
            st.setInt(5, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(String type, String name, Boolean status, String des, int subjectID, int order) {
        String sql = "INSERT INTO `spp_database`.`subjectsetting`\n"
                + "(\n"
                + "`name`,\n"
                + "`type`,\n"
                + "`descrption`,\n"
                + "`subjectID`,\n"
                + "`status`,\n"
                + "`order`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?)";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, type);
            st.setString(3, des);
            st.setInt(4, subjectID);
            st.setBoolean(5, status);
            st.setInt(6, order);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteByID(int id) {
        String sql = "delete\n"
                + "from subjectsetting\n"
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

    public List<SubjectSetting> getSubjectSettings(int subID, String searchValue, String status) {
        List<SubjectSetting> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String sql = "Select * from subjectsetting where subjectID = ?";
        params.add(subID);
        if (searchValue != null && !searchValue.trim().isEmpty()) {
            sql += " and (name like ? or type like ?)";
            params.add("%" + searchValue + "%");
            params.add("%" + searchValue + "%");
        }
        if (status != null && !status.trim().isEmpty()) {
            sql += " and status = ?";
            params.add(status.equals("1"));
        }
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            Helper.setParams(st, params);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SubjectSetting sb = new SubjectSetting();
                sb.setId(rs.getInt(1));
                sb.setName(rs.getString(2));
                sb.setType(rs.getString(3));
                sb.setDescription(rs.getString(4));
                sb.setStatus(rs.getBoolean(6));
                sb.setOrder(rs.getInt(7));
                sb.setSubject(new SubjectDAO().getAllSubjectById(subID));
                list.add(sb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
