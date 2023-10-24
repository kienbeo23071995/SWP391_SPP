/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.MD5;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;

/**
 *
 * @author efdf
 */
@WebServlet(name = "infouserServlet", urlPatterns = {"/infouser"})
public class InfouserServlet extends HttpServlet {

    private UserDAO udb = new UserDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String action = request.getParameter("action");

        if ("changePassword".equals(action)) {
            request.getRequestDispatcher("changepass.jsp").forward(request, response);
        } else {
            User us = (User) session.getAttribute("user");
            if (us != null) {
//                User u = udb.getAnUser(us.getEmail(), "");
//                request.setAttribute("anuser", u);
                request.getRequestDispatcher("infouser.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if ("changePassword".equals(action)) {
            String oldPass = request.getParameter("oldpass");
            String newPass = request.getParameter("newpass");
            String email = request.getParameter("email");
            User user = udb.chech(email, MD5.getMd5(oldPass));
            try {
                if (user != null) {
                    udb.updatePass2(email, MD5.getMd5(newPass));
                    request.setAttribute("UpdatePassSuccess", "Password has been changed, please log in again!");
                    session.removeAttribute("user");
                    request.getRequestDispatcher("notificationchange.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorUpdatePass", "Old Password is incorrect!");
                    request.getRequestDispatcher("changepass.jsp").forward(request, response);
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(InfouserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("updateProfile".equals(action)) {
            User us = (User) session.getAttribute("user");
            String fullname = request.getParameter("fullname");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            try {
                String emailCheck = "";
                String phoneCheck = "";
                User u2 = udb.getAnUser(us.getEmail(), us.getPhone());
                if (!u2.getEmail().equals(email)) {
                    emailCheck = email;
                }
                if (!u2.getPhone().equals(phone)) {
                    phoneCheck = phone;
                }
                User u = udb.getAnUser(emailCheck, phoneCheck);
                if ((u != null)) {
                    if (u.getEmail().equalsIgnoreCase(email) && (u.getUser_id() != us.getUser_id())) {
                        request.setAttribute("erroremail", "Email đã được sử dụng!");
                    }
                    if (u.getPhone().equals(phone) && (u.getUser_id() != us.getUser_id())) {
                        request.setAttribute("errorphone", "Số điện thoại đã được sử dụng!");
                    }
                    request.setAttribute("anuser", u2);
                    request.getRequestDispatcher("infouser.jsp").forward(request, response);
                } else {
                    User uNew = new User(us.getUser_id(), fullname, address, email, phone);
                    session.setAttribute("insertuser", uNew);
                    udb.updateUser(uNew);
                    session.removeAttribute("insertuser");
                    session.removeAttribute("user");
                    session.setAttribute("user", uNew);
                    response.sendRedirect("infouser.jsp");
                }
            } catch (NumberFormatException e) {
                request.getRequestDispatcher("infouser.jsp").forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
