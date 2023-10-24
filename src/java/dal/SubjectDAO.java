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
import model.Subject;
import ulti.Helper;

/**
 *
 * @author phuc2
 */
public class SubjectDAO extends BaseDAO {

    public ArrayList<Subject> getAllSubject() {
        ArrayList<Subject> sub = new ArrayList<>();
        String query = "Select * from `subject`";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject a = new Subject();
                a.setId(rs.getInt(1));
                a.setCode(rs.getString(2));
                a.setName(rs.getString(3));
                a.setDescription(rs.getString(4));
                a.setIsActive(rs.getBoolean(5));
                sub.add(a);
            }
        } catch (SQLException e) {

        }
        return sub;
    }

    public ArrayList<Subject> getAllSubjectWithSearch(String search, String status) {
        ArrayList<Subject> sub = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String query = "select * from subject where 1=1";
        if (search != null && !search.trim().isEmpty()) {
            query += " and (name like ? or code like ?)";
            params.add("%" + search + "%");
            params.add("%" + search + "%");
        }
        if (status != null && !status.trim().isEmpty()) {
            query += " and status = ?";
            params.add(status.equals("1"));
        }
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(query);
            Helper.setParams(st, params);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject a = new Subject();
                a.setId(rs.getInt(1));
                a.setCode(rs.getString(2));
                a.setName(rs.getString(3));
                a.setDescription(rs.getString(4));
                a.setIsActive(rs.getBoolean(5));
                sub.add(a);
            }
        } catch (SQLException e) {

        }
        return sub;
    }

    public void insertSubject(String code, String name, String descript, String status) {

        try {
            Connection connection = getJDBCConnection();
            String sql = "insert into subject (code, name, description, status)\n"
                    + "values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setString(3, descript);
            ps.setBoolean(4, status.equals("1"));
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public Subject getAllSubjectById(int id) {

        String sql = "select * from subject where id = ?";
        try {
            Connection connection = getJDBCConnection();

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject a = new Subject();
                a.setId(rs.getInt(1));
                a.setCode(rs.getString(2));
                a.setName(rs.getString(3));
                a.setDescription(rs.getString(4));
                a.setIsActive(rs.getBoolean(5));
                return a;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public void updateSubject(String code, String name, String descript, String status, int id) {
        String sql = "update subject\n"
                + "set code = ?,\n"
                + "    name = ?,\n"
                + "    description = ?,\n"
                + "    status = ?\n"
                + "    where id = ?";
        try {
            Connection connection = getJDBCConnection();

            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, code);
            st.setString(2, name);
            st.setString(3, descript);
            st.setBoolean(4, status.equals("1"));
            st.setInt(5, id);
            st.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public void delete(int id) {
        String sql = "delete from subject where id = ?";

        try {
            Connection connection = getJDBCConnection();

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Subject> getListByPage(ArrayList<Subject> list, int start, int end) {
        ArrayList<Subject> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public static void main(String[] args) {

        SubjectDAO c = new SubjectDAO();
        List<Subject> list = c.getAllSubject();

        for (Subject o : list) {
            System.out.println(o);
        }
    }
}
