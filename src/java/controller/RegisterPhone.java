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

/**
 *
 * @author SHD
 */
@WebServlet(name = "RegisterPhone", urlPatterns = {"/registerPhone"})
public class RegisterPhone extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("registerPhone.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if(new UserDAO().checkExistPhone(phone)){
            request.setAttribute("error", "Phone exist");
        }else{
            request.setAttribute("success", "Get OTP to verify");
        }
        request.setAttribute("phone", phone);
        request.setAttribute("name", name);
        request.setAttribute("password", password);
        request.getRequestDispatcher("registerPhone.jsp").forward(request, response);
        
    }

}
