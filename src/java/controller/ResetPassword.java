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
@WebServlet(name = "ResetPassword", urlPatterns = {"/reset"})
public class ResetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("phone") != null) {
            String phone = request.getParameter("phone");
            request.setAttribute("phone", phone);

        } else {
            String email = request.getParameter("email");
            request.setAttribute("email", email);

        }
        request.getRequestDispatcher("reset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pass_one = request.getParameter("pass_one");
        String pass_two = request.getParameter("pass_two");
        if (!pass_one.equals(pass_two)) {
            request.setAttribute("error", "Password must equal Re password");

        } else {
            if (request.getParameter("email") != null) {
                request.setAttribute("email", request.getParameter("email"));

                new UserDAO().updatePass(MD5.getMd5(pass_one), request.getParameter("email"));
               

            } else {
                request.setAttribute("phone", request.getParameter("phone"));

                new UserDAO().updatePassPhone(MD5.getMd5(pass_one), request.getParameter("phone"));
                

            }
            request.setAttribute("success", "Success!");
        }
        request.getRequestDispatcher("reset.jsp").forward(request, response);
    }

}
