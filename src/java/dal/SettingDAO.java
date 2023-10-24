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
import model.Setting;
import ulti.Helper;

/**
 *
 * @author SHD
 */
public class SettingDAO extends BaseDAO {

    public List<Setting> getList(String name,String type,String status) {
        List<Setting> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String sql = "SELECT * FROM spp_database.setting where 1=1";
        try {
            if(name != null && !name.trim().isEmpty()){
                sql += " and name like ?";
                params.add("%" + name + "%");
            }
            if(type != null && !type.trim().isEmpty()){
                sql += " and setting.type like ?";
                params.add("%" + type + "%");
            }
            if(status != null && !status.equals("0") && !status.isEmpty()){
                sql += " and setting.is_active = ?";
                params.add(status.equals("1"));
            }
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            Helper.setParams(st, params);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Setting s = new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                list.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Setting getSetting(int id) {
       
        String sql = "SELECT * FROM spp_database.setting where setting_id = ?";
        try {

            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Setting s = new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new SettingDAO().getSetting(2).getName());
    }

    public void update(String type, String name, int is_active,int id) {
        try {
            String sql = "update spp_database.setting set type = ?,name = ?,is_active = ? where setting_id = ?";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setString(1, type);
            ps.setString(2, name);
            ps.setInt(3, is_active);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String sql = "delete from spp_database.setting where setting_id = ?";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteClass(int id) {
        try {
            String sql = "delete from spp_database.semester_class where setting_id = ?";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(String type, String name, int is_active) {
         try {
            String sql = "INSERT INTO spp_database.setting (type,name,is_active) \n"
                    + "VALUES ( ?,?,?)";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setString(1, type);
            ps.setInt(3, is_active);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    public boolean checkExist(String type, String name) {
        String sql = "SELECT * FROM spp_database.setting where type = ? and name = ?";
        try {

            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, type);
            st.setString(2, name);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
