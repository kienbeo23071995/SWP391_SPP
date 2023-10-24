/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import controller.SendEmail;
import dal.MD5;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import model.RoleName;
import model.User;
import ulti.Constants;
import ulti.Helper;

/**
 *
 * @author asus
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "UserList", urlPatterns = {"/UserList"})
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        int pagenum = request.getParameter("pagenum") != null ? Integer.parseInt(request.getParameter("pagenum")) : 1;
        UserDAO dao = new UserDAO();
        ArrayList<User> list = dao.getAllUser();
        String action = request.getParameter("action");
        if (action == null || action.length() == 0) { // when access to userList     
            ArrayList<RoleName> listC = dao.getAllCategory();            
            request.setAttribute("rolename", listC);           
        } else if (action.equalsIgnoreCase("add")) { // when access to add user
            request.getRequestDispatcher("AddUser.jsp").forward(request, response);
            return;
        } else if (action.equalsIgnoreCase("update")) { // when access to update user
            String uid = request.getParameter("uid");
            int id = Integer.parseInt(uid);
            User user = dao.getUserById(id);
            request.setAttribute("user", user);
            
        } else if (action.equalsIgnoreCase("delete")) { // when access to delete
            String uid = request.getParameter("uid");;
            int id = Integer.parseInt(uid);
            dao.DeleteUser(id);
            response.sendRedirect("UserList");
        } else if (action.equalsIgnoreCase("search")) {
            String search = request.getParameter("search");
            list = dao.searchUser(search);
            request.setAttribute("user", list);
            
        } else if (action.equalsIgnoreCase("searchRole")) {
            String role_id = request.getParameter("rid");
            list = dao.getAllUserByRID(role_id);
            ArrayList<model.RoleName> listC = dao.getAllCategory();
            request.setAttribute("user", list);
            request.setAttribute("rolename", listC);           
        } else if (action.equals("searchStatus")) {
            String status = request.getParameter("status");
            try {
                int real_status = Integer.parseInt(status);
                list = dao.getAllUserByStatus(real_status);
                ArrayList<model.RoleName> listC = dao.getAllCategory();
                request.setAttribute("user", list);
                request.setAttribute("rolename", listC);
                
            } catch (Exception e) {
                response.sendRedirect("UserList");
            }

        } else {
            response.sendRedirect("UserList");
            return;
        }
        List users = Helper.pagination(list, pagenum, 5);
        request.setAttribute("pagenum", pagenum);
        request.setAttribute("totalPage", list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1));
        request.setAttribute("user", users);
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.length() == 0) { // when access to userList
            UserDAO dao = new UserDAO();
            ArrayList<User> list = dao.getAllUser();
            ArrayList<RoleName> listC = dao.getAllCategory();
            request.setAttribute("user", list);
            request.setAttribute("rolename", listC);
            request.getRequestDispatcher("userList.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("add")) { // when access to add user
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String avatar = uploadFile(request);
            String role_id = request.getParameter("role_id");
            String status = "0";
            UserDAO dbUser = new UserDAO();
            boolean checkEmail = dbUser.checkExist(email);
            boolean checkPhone = dbUser.checkExistPhone(phone);
            if (checkEmail || checkPhone || !email.endsWith("@fpt.edu.vn")) {
                request.setAttribute("name", name);
                request.setAttribute("address", address);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("role_id", role_id);
                String mess = "Email must be unique and end with @fpt.edu.vn or phone is duplicate!!!";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("AddUser.jsp").forward(request, response);
                return;
            }
            UserDAO user = new UserDAO();
            user.insertUser(name, address, email, phone, avatar, role_id, status);
            String url = request.getContextPath() + "/VerifyAccount?email=" + email;
            String password = SendEmail.getAlphaNumericString(8);
            user.updatePass(MD5.getMd5(password), email);
            SendEmail.send(email, "Verify Account", "Please click <a href='" + url + "'>here</a> to active account.Password is : " + password);
            String nofi = "Add user successfully!!Please check email and verify to active account";
            request.setAttribute("nofi", nofi);
            request.getRequestDispatcher("AddUser.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("update")) { // when access to update user
            //String name, String address, String email, String phone, String pass,String avatar, int role_id, int status
            String uid = request.getParameter("uid");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String avatar = request.getParameter("avatar");
            String role_id = request.getParameter("role_id");
            String status = request.getParameter("status");
            int id = Integer.parseInt(uid);
            UserDAO dao = new UserDAO();
            boolean checkEmail = dao.checkDuplicateEmail(email);
            boolean checkPhone = dao.checkDuplicatePhone(phone);
            if (checkEmail || checkPhone || !email.endsWith("@fpt.edu.vn")) {
                request.setAttribute("name", name);
                request.setAttribute("address", address);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("role_id", role_id);
                request.setAttribute("status", status);
                String mess = "Email must be unique and end with @fpt.edu.vn or phone is duplicate!!!";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("updateUser.jsp").forward(request, response);
            } else {
                dao.updateUser(name, email, role_id, status, phone, id);
                String mess = "Update successfully!!";
                request.setAttribute("mess", mess);
                User user = dao.getUserById(id);
                request.setAttribute("user", user);
                request.getRequestDispatcher("updateUser.jsp").forward(request, response);
            }
        } else if (action.equalsIgnoreCase("delete")) { // when access to delete
            String uid = request.getParameter("uid");;
            int id = Integer.parseInt(uid);
            UserDAO dao = new UserDAO();
            dao.DeleteUser(id);
            response.sendRedirect("UserList");
        } else if (action.equalsIgnoreCase("search")) {
            String search = request.getParameter("search");
            UserDAO dao = new UserDAO();
            ArrayList<User> list = dao.searchUser(search);
            request.setAttribute("user", list);
            request.getRequestDispatcher("userList.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("searchRole")) {
            String role_id = request.getParameter("rid");
            UserDAO dao = new UserDAO();
            List<User> list = dao.getAllUserByRID(role_id);
            ArrayList<model.RoleName> listC = dao.getAllCategory();
            request.setAttribute("user", list);
            request.setAttribute("rolename", listC);
            request.getRequestDispatcher("userList.jsp").forward(request, response);
        } else {
            response.sendRedirect("UserList");
        }
    }
    
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return Constants.DEFAULT_FILENAME;
    }
    
    public String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        int length = getServletContext().getRealPath("/").length();
        String uploadPath = new StringBuilder(getServletContext().getRealPath("/")).delete(length - 10, length - 4).toString() + File.separator + "images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            for (Part part : request.getParts()) {
                String temp = getFileName(part);
                if (!temp.equals(Constants.DEFAULT_FILENAME) && !temp.trim().isEmpty()) {
                    fileName = temp;
                    part.write(uploadPath + File.separator + fileName);
                    break;
                }
            }
        } catch (FileNotFoundException fne) {
        }
        return fileName;
    }

}
