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
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "VerifyCode", urlPatterns = {"/VerifyCode"})
public class VerifyCode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("action", request.getParameter("action"));
        request.getRequestDispatcher("verify.jsp").forward(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int code = Integer.parseInt(request.getParameter("code"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("code", code);
        request.setAttribute("action", request.getParameter("action"));
        if (request.getParameter("action").equals("register")) {
            if (code == new UserDAO().getCode(request.getParameter("email"))) {
                request.setAttribute("success", "<a href=" + "login" + ">Register successfull!Back to login please</a>");
                new UserDAO().updateStatus(request.getParameter("email"));

            } else {
                request.setAttribute("error", "Wrong code");
                
            }
            request.getRequestDispatcher("verify.jsp").forward(request, response);

            
        } else {
            if (code == new UserDAO().getCode(request.getParameter("email"))) {
               response.sendRedirect("reset?email=" + request.getParameter("email"));

            } else {
                request.setAttribute("error", "Wrong code");
                request.getRequestDispatcher("verify.jsp").forward(request, response);
            }
            
        }
        
    }

}
