/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MD5;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author SHD
 */
@WebServlet(name = "RegisterEmail", urlPatterns = {"/RegisterEmail"})
public class RegisterEmail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        if (new UserDAO().checkExist(email) == true || !email.endsWith("@fpt.edu.vn")) {
            request.setAttribute("email", email);
            request.setAttribute("name", name);
            request.setAttribute("password", password);
            request.setAttribute("error", "Email phải không trùng và có đuôi @fpt.edu.vn");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            int random = new SendEmail().getRandom();
            new SendEmail().send(email, "Code Verify", random + "");
            new UserDAO().insertVerify(email, random, MD5.getMd5(password), name);
            response.sendRedirect("VerifyCode?email=" + email + "&action=register");
        }
    }

}
