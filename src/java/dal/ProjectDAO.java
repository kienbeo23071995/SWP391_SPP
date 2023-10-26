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
import model.Group;
import model.Project;
import model.User;
import ulti.Helper;

/**
 *
 * @
 */
public class ProjectDAO extends BaseDAO {

    public Project getProjectByID(int id) {
        String sql = "select * from project where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt(1));
                project.setCode(rs.getString(2));
                project.setEngname(rs.getString(3));
                project.setViname(rs.getString(4));
                project.setStatus(rs.getBoolean(5));
                GroupDAO groupDAO = new GroupDAO();
                Group group = groupDAO.getGroupByID(rs.getInt(6));
                project.setGroup(group);
                UserDAO udo = new UserDAO();
                User user = udo.getUserById(rs.getInt(7));
                project.setMentor(user);
                return project;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Project> getAll(String searchName, String status, String groupID) {
        List<Project> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String sql = "select * from project where 1=1 ";
        try {
            if (searchName != null && !searchName.trim().isEmpty()) {
                sql += " and (code like ? or eng_name like ? or vietnamese_name like ?)";
                params.add("%" + searchName + "%");
                params.add("%" + searchName + "%");
                params.add("%" + searchName + "%");
            }
            if (status != null && !status.trim().isEmpty() && !status.equals("0")) {
                sql += " and status = ?";
                params.add(status.equals("1"));
            }
            if (groupID != null && !groupID.trim().isEmpty() && !groupID.trim().equals("0")) {
                sql += " and groupID = ?";
                params.add(Integer.parseInt(groupID));
            }
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            Helper.setParams(st, params);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt(1));
                project.setCode(rs.getString(2));
                project.setEngname(rs.getString(3));
                project.setViname(rs.getString(4));
                project.setStatus(rs.getBoolean(5));
                project.setDescription(rs.getString(7));
                GroupDAO groupDAO = new GroupDAO();
                Group group = groupDAO.getGroupByID(rs.getInt(6));
                project.setGroup(group);
                UserDAO udo = new UserDAO();
                User user = udo.getUserById(rs.getInt(8));
                project.setMentor(user);
                list.add(project);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertProject(String code, String engname, String viname, String description, int groupID) {
        String sql = "insert into project (code, eng_name, vietnamese_name, status, groupID, description)\n"
                + "values (?,?,?,0,?,?)";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            st.setString(2, engname);
            st.setString(3, viname);
            st.setInt(4, groupID);
            st.setString(5, description);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProject(String id, String engname, String viname, String description, int groupID) {
        String sql = "update project set groupID = ?,vietnamese_name = ?,eng_name = ?,description = ? where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, groupID);
            st.setString(2, viname);
            st.setString(3, engname);
            st.setString(4, description);
            st.setInt(5, Integer.parseInt(id));
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteByID(int id) {
        String sql = "delete from project where id = ?";
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
