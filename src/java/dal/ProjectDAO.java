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
import model.Project;
import model.Subject;
import ulti.Helper;

/**
 *
 * @
 */
public class ProjectDAO extends BaseDAO {

    public Project getProjectByID(int id) {
        String sql = "select * from project where project.id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt(1));
                project.setProjectName(rs.getString(2));
                project.setStartDate(rs.getTimestamp(5).toLocalDateTime());
                project.setEndDate(rs.getTimestamp(6).toLocalDateTime());
                project.setDescription(rs.getString(4));
                SubjectDAO subjectDAO = new SubjectDAO();
                Subject subject = subjectDAO.getAllSubjectById(rs.getInt(3));
                project.setSubject(subject);
                return project;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Project> getAll(String searchName,String searchSubject) {
        List<Project> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String sql = "select * from project where 1=1 ";
        try {
            if(searchName != null && !searchName.trim().isEmpty()){
                sql += " and name like ? ";
                params.add("%" + searchName + "%");
            }
            if(searchSubject != null && !searchSubject.trim().isEmpty()){
                sql += " and subjectID = ?";
                params.add(Integer.parseInt(searchSubject));
            }
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            Helper.setParams(st, params);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt(1));
                project.setProjectName(rs.getString(2));
                project.setStartDate(rs.getTimestamp(5).toLocalDateTime());
                project.setEndDate(rs.getTimestamp(6).toLocalDateTime());
                project.setDescription(rs.getString(4));
                SubjectDAO subjectDAO = new SubjectDAO();
                Subject subject = subjectDAO.getAllSubjectById(rs.getInt(3));
                project.setSubject(subject);
                list.add(project);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertProject(String name, String subjectID, String description, String startDate, String endDate) {
        String sql = "insert into project ( name, subjectID, description, startdate, enddate)\n"
                + "values (?,?,?,?,?)";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, Integer.parseInt(subjectID));
            st.setString(3, description);
            st.setTimestamp(4, Timestamp.valueOf(startDate));
            st.setTimestamp(5, Timestamp.valueOf(endDate));
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProject(String id, String name, String subjectID, String description, String startDate, String endDate) {
        String sql = "update project\n"
                + "set name = ?,\n"
                + "    startdate = ?,\n"
                + "    enddate = ?,\n"
                + "    description = ?,\n"
                + "    subjectID = ?\n"
                + "where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(5, Integer.parseInt(subjectID));
            st.setString(4, description);
            st.setTimestamp(2, Timestamp.valueOf(startDate));
            st.setTimestamp(3, Timestamp.valueOf(endDate));
            st.setInt(6, Integer.parseInt(id));
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteByID(int id) {
        String sql = "delete\n"
                + "from project\n"
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
}
