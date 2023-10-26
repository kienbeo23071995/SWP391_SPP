/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.BaseDAO.getJDBCConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RoleName;
import model.User;
import ulti.Helper;

/**
 *
 * @author Admin
 */
public class UserDAO extends BaseDAO {

    public User chech(String Xemail, String Xpassword) {
        String sql = "SELECT * FROM spp_database.user where email = ? and password = ? and status = 1";
        try {

            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Xemail);
            st.setString(2, Xpassword);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone_number"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getAnUser(String xEmail, String xPhoneNumber) {
        String sql = "SELECT * FROM spp_database.user WHERE email = ? OR phone_number = ?";

        User u = null;
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, xEmail);
            st.setString(2, xPhoneNumber);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone_number"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return (u);
    }

    public void updateUser(User u) {
        String sql = "UPDATE spp_database.user SET fullname = ? ,address = ? ,email=? ,phone_number = ? WHERE user_id = ? ";

        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getFullname());
            st.setString(2, u.getAddress());
            st.setString(3, u.getEmail());
            st.setString(4, u.getPhone());
            st.setInt(5, u.getUser_id());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updatePass2(String xEmail, String xPass) throws NoSuchAlgorithmException {

        String sql = "update spp_database.user set password = ? where email = ?";

        try {
            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, xPass);
            st.setString(2, xEmail);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public boolean checkExist(String email) {
        String sql = "SELECT * FROM spp_database.user where email = ? and status = 1";
        try {

            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2));
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistPhone(String phone) {
        String sql = "SELECT * FROM spp_database.user where phone_number = ?";
        try {

            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2));
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkDuplicateEmail(String email) {
        // Kết nối cơ sở dữ liệu và kiểm tra sự trùng lặp của email
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        try {
            // Thực hiện truy vấn kiểm tra email trùng lặp
            // Ví dụ, sử dụng PreparedStatement để thực hiện truy vấn SQL

            Connection connection = getJDBCConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 1) {
                    return true;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi truy vấn cơ sở dữ liệu
        }

        return false; // Trả về true nếu có lỗi xảy ra
    }

    public boolean checkDuplicatePhone(String phone) {
        // Kết nối cơ sở dữ liệu và kiểm tra sự trùng lặp của email
        String query = "SELECT COUNT(*) FROM user WHERE phone_number = ?";
        try {
            // Thực hiện truy vấn kiểm tra email trùng lặp
            // Ví dụ, sử dụng PreparedStatement để thực hiện truy vấn SQL

            Connection connection = getJDBCConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi truy vấn cơ sở dữ liệu
        }

        return false; // Trả về true nếu có lỗi xảy ra
    }

    public void insertVerify(String email, int random, String password, String name) {
        try {
            String sql = "INSERT INTO spp_database.user (email,verify,password,fullname,status,role_id) \n"
                    + "VALUES ( ?,?,?,?,NULL,6)";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, random);
            ps.setString(3, password);
            ps.setString(4, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCode(String parameter) {
        String sql = "SELECT verify  FROM spp_database.user where email = ?";
        try {

            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, parameter);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateStatus(String parameter) {
        try {
            String sql = "update spp_database.user set status = 1 where email = ?";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setString(1, parameter);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String email) {
        String sql = "SELECT * FROM spp_database.user u inner join cate_role cr on u.role_id=cr.rid where email = ?";
        try {

            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                RoleName rn = new RoleName();
                rn.setRid(rs.getInt("rid"));
                rn.setRname(rs.getString("rname"));
                user.setRole_id(rn);
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                return user;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateVerify(String email, int random) {
        try {
            String sql = "update spp_database.user set verify = ? where email = ?";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setString(2, email);
            ps.setInt(1, random);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePass(String pass_one, String email) {
        try {
            String sql = "update spp_database.user set password = ? where email = ?";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setString(2, email);
            ps.setString(1, pass_one);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPhone(String phone, String name, String pass) {
        try {
            String sql = "INSERT INTO spp_database.user (status,phone_number,password,fullname) \n"
                    + "                    VALUES ( 1,?,?,?)";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, pass);
            ps.setString(3, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByPhone(String parameter) {
        String sql = "SELECT * FROM spp_database.user u inner join cate_role cr on u.role_id=cr.rid where phone_number = ?";
        try {

            Connection connection = getJDBCConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, parameter);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                RoleName rn = new RoleName();
                rn.setRid(rs.getInt("rid"));
                rn.setRname(rs.getString("rname"));
                user.setRole_id(rn);
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                return user;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updatePassPhone(String pass_one, String parameter) {
        try {
            String sql = "update spp_database.user set password = ? where phone_number = ?";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setString(2, parameter);
            ps.setString(1, pass_one);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user u join cate_role c on u.role_id = c.rid;";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                RoleName rn = new RoleName();
                rn.setRid(rs.getInt("rid"));
                rn.setRname(rs.getString("rname"));
                user.setRole_id(rn);
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<RoleName> getAllCategory() {
        ArrayList<RoleName> list = new ArrayList<>();
        String sql = "SELECT * FROM cate_role";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RoleName(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public RoleName getCategoryByID(int id) {
        ArrayList<RoleName> list = new ArrayList<>();
        String sql = "SELECT * FROM cate_role  where rid = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new RoleName(rs.getInt(1), rs.getString(2));
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public ArrayList<User> getAllUserByRID(String rid) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "Select *from user u inner join cate_role cr on u.role_id=cr.rid where role_id=?;";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, rid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                RoleName rn = new RoleName();
                rn.setRid(rs.getInt("rid"));
                rn.setRname(rs.getString("rname"));
                user.setRole_id(rn);
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                list.add(user);
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public ArrayList<User> getAllUserByStatus(int status) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "Select *from user u inner join cate_role cr on u.role_id=cr.rid where role_id=?;";
        if (status == 1) {
            sql = "Select *from user u inner join cate_role cr on u.role_id=cr.rid where u.status=1;";
        } else {
            sql = "Select *from user u inner join cate_role cr on u.role_id=cr.rid where u.status=0 or u.status is null;";
        }
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                RoleName rn = new RoleName();
                rn.setRid(rs.getInt("rid"));
                rn.setRname(rs.getString("rname"));
                user.setRole_id(rn);
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                list.add(user);
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public void insertUser(String name, String address, String email, String phone, String avatar, String role_id, String status) {
        try {
            String sql = "INSERT INTO `spp_database`.`user`\n"
                    + "(`fullname`,\n"
                    + "`address`,\n"
                    + "`email`,\n"
                    + "`avatar`,\n"
                    + "`role_id`,\n"
                    + "`status`,\n"
                    + "`phone_number`)\n"
                    + "VALUES\n"
                    + "(?, ?, ?, ?, ?, ?, ?);";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, email);
            ps.setString(4, avatar);
            ps.setString(5, role_id);
            ps.setString(6, status);
            ps.setString(7, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM user u inner join cate_role cr on u.role_id=cr.rid where user_id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                RoleName rn = new RoleName();
                rn.setRid(rs.getInt("rid"));
                rn.setRname(rs.getString("rname"));
                user.setRole_id(rn);
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                return user;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public ArrayList<User> searchUser(String search) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user u inner join cate_role cr on u.role_id=cr.rid where fullname like ? or "
                + "phone_number like ? or email like ? or address like ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setString(4, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                RoleName rn = new RoleName();
                rn.setRid(rs.getInt("rid"));
                rn.setRname(rs.getString("rname"));
                user.setRole_id(rn);
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                list.add(user);
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public void DeleteUser(int id) {
        String sql = " DELETE FROM user WHERE user_id =?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateUser(String name, String email, String role_id, String stus, String phone, int id) {
        try {
            String sql = "UPDATE `user`\n"
                    + "SET\n"
                    + "`fullname` = ?,\n"
                    + "`email` = ?,\n"
                    + "`role_id` = ?,\n"
                    + "`status` =?,\n"
                    + "`phone_number` =?\n"
                    + "WHERE `user_id` =?;";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);

            ps.setString(2, email);

            ps.setString(3, role_id);
            ps.setString(4, stus);
            ps.setString(5, phone);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user u inner join cate_role cr on u.role_id=cr.rid where email = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                RoleName rn = new RoleName();
                rn.setRid(rs.getInt("rid"));
                rn.setRname(rs.getString("rname"));
                user.setRole_id(rn);
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                return user;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public void activeAccount(String email) {
        try {
            String sql = "update user set status = 1 where email = ?";
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String email, String password) {
        String sql = "insert into spp_database.user (email, password,role_id,status)\n"
                + "values (?,?,6,1)";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<User> getAllUserByClass(String searchValue, int subID) {
        List<User> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String sql = "select * from user join class_user cu on user.user_id = cu.user_id where class_id = ?";
        params.add(subID);
        if (searchValue != null && !searchValue.trim().isEmpty()) {
            sql += " and ( fullname like ? or email like ? )";
            params.add(searchValue);
            params.add(searchValue);
        }
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            Helper.setParams(ps, params);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                list.add(user);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public void removeStudent(int userID, int classID) {
        String sql = "delete from class_user where class_id = ? and user_id = ?";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classID);
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<User> getStudentByConditions(String classID,int subjectID) {
        List<User> list = new ArrayList<>();
        String sql = "select * from user where user_id not in (select user_id from class_user where class_id = ? union\n"
                + "select user_id from class_user where class_id not in (select class_id from class where subjectID = ?))   ";
        try {
            Connection connection = getJDBCConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(classID));
            ps.setInt(2, subjectID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setAvatar(rs.getString("avatar"));
                user.setStatus(rs.getInt("status"));
                user.setPhone(rs.getString("phone_number"));
                list.add(user);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public void addUserToClass(String[] ids, String classID) {
        String sql = "insert into class_user (class_id, user_id)\n"
                + "values (?,?)";
        try {
            for (String item : ids) {
                Connection connection = getJDBCConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(2, Integer.parseInt(item));
                ps.setInt(1, Integer.parseInt(classID));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
        }
    }
}
