/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.Project;
import ulti.Helper;

/**
 *
 * @
 */
public class AssignmentDAO extends BaseDAO {

    public void insertAssignment(String title, String startDate, String endDate, String description, int classID) {
        String sql = "insert into assignment (title, description, startdate, enddate, classID)\n"
                + "values (?,?,?,?,?)";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTimestamp(3, new Timestamp(Helper.getDatefromString(startDate).getTime()));
            st.setTimestamp(4, new Timestamp(Helper.getDatefromString(endDate).getTime()));
            st.setString(1, title);
            st.setString(2, description);
            st.setInt(5, classID);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Assignment getAssignmentByID(int id) {
        String sql = "select * from assignment where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment as = new Assignment();
                as.setId(rs.getInt(1));
                as.setTitle(rs.getString(2));
                as.setDescription(rs.getString(3));
                as.setStartdate(rs.getTimestamp(4).toLocalDateTime());
                as.setEnddate(rs.getTimestamp(5).toLocalDateTime());
                as.setClassid(new ClassDAO().getClassByID(rs.getInt(6)));
                return as;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void UpdateAssignment(String title, LocalDateTime startDate, LocalDateTime endDate, String attachment, int projectID, int id) {
        String sql = "update assignment\n"
                + "set projectID = ?,\n"
                + "    attachment = ?,\n"
                + "    startdate = ?,\n"
                + "    enddate = ?,\n"
                + "    title = ?\n"
                + "where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, projectID);
            st.setString(2, attachment);
            st.setTimestamp(3, Timestamp.valueOf(startDate));
            st.setTimestamp(4, Timestamp.valueOf(endDate));
            st.setString(5, title);
            st.setInt(6, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteByID(int id) {
        String sql = "delete from assignment where id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Assignment> getAssignments(String searchValue, String startDate, String endDate, int subID) {
        List<Assignment> list = new ArrayList<>();
        String sql = "select * from assignment a join class c on a.classID = c.id where c.subjectID = ?";
        List<Object> params = new ArrayList<>();
        params.add(subID);
        if (searchValue != null && !searchValue.trim().isEmpty()) {
            sql += " and title like ?";
            params.add("%" + searchValue + "%");
        }
        if (startDate != null && !startDate.trim().isEmpty()) {
            sql += " and startdate >= ?";
            params.add(Helper.getDatefromString(startDate));
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            sql += " and enddate <= ?";
            params.add(Helper.getDatefromString(endDate));
        }
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            Helper.setParams(st, params);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment as = new Assignment();
                as.setId(rs.getInt(1));
                as.setTitle(rs.getString(2));
                as.setDescription(rs.getString(3));
                as.setStartdate(rs.getTimestamp(4).toLocalDateTime());
                as.setEnddate(rs.getTimestamp(5).toLocalDateTime());
                as.setClassid(new ClassDAO().getClassByID(rs.getInt(6)));
                list.add(as);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Assignment> getAssignmentByClass(String searchValue, String startDate, String endDate, int classID) {
        List<Assignment> list = new ArrayList<>();
        String sql = "select * from assignment where classID = ?";
        List<Object> params = new ArrayList<>();
        params.add(classID);
        if (searchValue != null && !searchValue.trim().isEmpty()) {
            sql += " and title like ?";
            params.add("%" + searchValue + "%");
        }
        if (startDate != null && !startDate.trim().isEmpty()) {
            sql += " and startdate >= ?";
            params.add(Helper.getDatefromString(startDate));
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            sql += " and enddate <= ?";
            params.add(Helper.getDatefromString(endDate));
        }
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            Helper.setParams(st, params);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment as = new Assignment();
                as.setId(rs.getInt(1));
                as.setTitle(rs.getString(2));
                as.setDescription(rs.getString(3));
                as.setStartdate(rs.getTimestamp(4).toLocalDateTime());
                as.setEnddate(rs.getTimestamp(5).toLocalDateTime());
                as.setClassid(new ClassDAO().getClassByID(rs.getInt(6)));
                list.add(as);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
