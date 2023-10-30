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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Class;
import model.Subject;
import ulti.Helper;

/**
 *
 * @author nocol
 */
public class ClassDAO extends BaseDAO {

    public List<Class> getAll(String search, String status, int subjectID) {
        List<Class> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String sql = "Select * from class where subjectID = ?";
        params.add(subjectID);
        if (search != null && !search.trim().isEmpty()) {
            sql += " and (name like ? or code like ?)";
            params.add("%" + search + "%");
            params.add("%" + search + "%");
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
                Class cls = new Class();
                cls.setId(rs.getInt(1));
                cls.setCode(rs.getString(2));
                cls.setName(rs.getString(5));
                cls.setStatus(rs.getBoolean(4));
                Subject sub = new SubjectDAO().getAllSubjectById(subjectID);
                cls.setSubject(sub);
                list.add(cls);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void updateAssignment(int id, String startDate, String endDate, String title, String description) {
        String sql = "update assignment\n"
                + "set startdate = ?,\n"
                + "    enddate = ?,\n"
                + "    title = ?,\n"
                + "    description = ?\n"
                + "where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTimestamp(1, new Timestamp(Helper.getDatefromString(startDate).getTime()));
            st.setTimestamp(2, new Timestamp(Helper.getDatefromString(endDate).getTime()));
            st.setString(3, title);
            st.setString(4, description);
            st.setInt(5, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertClass(String code, String detail, String status, int subjectID) {
        String sql = "insert into class (code, subjectID, status, name)\n"
                + "values (?,?,?,?)";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            st.setString(4, detail);
            st.setBoolean(3, status.equals("1"));
            st.setInt(2, subjectID);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteByID(int parseInt) {
        String sql = "delete from class where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, parseInt);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Class getClassByID(int aInt) {
        String sql = "select * from class where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, aInt);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Class cls = new Class();
                cls.setId(rs.getInt(1));
                cls.setCode(rs.getString(2));
                cls.setName(rs.getString(5));
                cls.setStatus(rs.getBoolean(4));
                Subject sub = new SubjectDAO().getAllSubjectById(rs.getInt(3));
                cls.setSubject(sub);
                return cls;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateClass(int id, String code, String name, String status, int subID) {
        String sql = "UPDATE `spp_database`.`class`\n"
                + "SET\n"
                + "`code` = ?,\n"
                + "`subjectID` = ?,\n"
                + "`status` = ?,\n"
                + "`name` = ?\n"
                + "WHERE `id` = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            st.setString(4, name);
            st.setBoolean(3, status.equals("1"));
            st.setInt(2, subID);
            st.setInt(5, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNumberOfStudents(int classID) {
        String sql = "select count(*) as num from class_user where class_id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, classID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<Class> getAll(String search, String status) {
        List<Class> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String sql = "Select * from class where 1=1";
        if (search != null && !search.trim().isEmpty()) {
            sql += " and (name like ? or code like ?)";
            params.add("%" + search + "%");
            params.add("%" + search + "%");
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
                Class cls = new Class();
                cls.setId(rs.getInt(1));
                cls.setCode(rs.getString(2));
                cls.setName(rs.getString(5));
                cls.setStatus(rs.getBoolean(4));
                Subject sub = new SubjectDAO().getAllSubjectById(rs.getInt(3));
                cls.setSubject(sub);
                list.add(cls);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Class> getClassesBySubID(int subjectID) {
        List<Class> list = new ArrayList<>();
        String sql = "Select * from class where subjectID = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, subjectID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Class cls = new Class();
                cls.setId(rs.getInt(1));
                cls.setCode(rs.getString(2));
                cls.setName(rs.getString(5));
                cls.setStatus(rs.getBoolean(4));
                Subject sub = new SubjectDAO().getAllSubjectById(rs.getInt(3));
                cls.setSubject(sub);
                list.add(cls);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
