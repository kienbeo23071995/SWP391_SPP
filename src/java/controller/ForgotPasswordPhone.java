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
@WebServlet(name = "ForgotPasswordPhone", urlPatterns = {"/forgotPhone"})
public class ForgotPasswordPhone extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("forgotPasswordPhone.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phone = request.getParameter("phone");
        
        if(new UserDAO().checkExistPhone(phone)){
            request.setAttribute("success", "Phone exist");
        }else{
           
            request.setAttribute("erorr", "Phone not exist");
        }
       request.setAttribute("phone", phone);
        request.getRequestDispatcher("forgotPasswordPhone.jsp").forward(request, response);
    }

}
