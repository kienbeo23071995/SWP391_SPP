/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author SHD
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgot"})
public class ForgotPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("forgot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        User u = new UserDAO().getUser(email);
        if (u == null || u.getStatus() != 1) {
            request.setAttribute("email", email);
            request.setAttribute("error", "Email not exist or not verify!");
            request.getRequestDispatcher("forgot.jsp").forward(request, response);
        } else {
            int random = new SendEmail().getRandom();
            new SendEmail().send(email, "Code Verify", random+"");
            new UserDAO().updateVerify(email,random);
            response.sendRedirect("VerifyCode?email="+email+"&action=reset");
        }
    }


}
