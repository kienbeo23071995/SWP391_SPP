/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.classmanager;

import dal.ClassDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Class;
import model.Subject;
import ulti.Helper;
import ulti.RestAPI;
import static ulti.RestAPI.getJsonArray;

/**
 *
 * @
 */
public class classList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pagenum = request.getParameter("pagenum") != null ? Integer.parseInt(request.getParameter("pagenum")) : 1;
        String search = request.getParameter("search");
        request.setAttribute("search", search);
        String status = request.getParameter("status");
        request.setAttribute("status", status);
        String subjectID = request.getParameter("subjectID");
        request.setAttribute("subID", subjectID);
        ClassDAO classDAO = new ClassDAO();
        List<Class> list = classDAO.getAll(search, status);
        List<model.Class> pagination = Helper.pagination(list, pagenum, 5);
        int totalPage = list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1);
        request.setAttribute("pagenum", pagenum);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("classes", pagination);
        SubjectDAO subjectDAO = new SubjectDAO();
        ArrayList<Subject> listSubject = subjectDAO.getAllSubject();
        request.setAttribute("subjects", listSubject);
        request.getRequestDispatcher("ClassManager.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        String action = request.getParameter("action");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String subjectID = request.getParameter("subjectID");
        String status = request.getParameter("status");
        ClassDAO classDAO = new ClassDAO();
        if (action.equals("add")) {
            classDAO.insertClass(code, name, status, Integer.parseInt(subjectID));
        } else {
            String id = request.getParameter("id");
            classDAO.updateClass(Integer.parseInt(id), code, name, status, Integer.parseInt(subjectID));
        }
        String url = request.getHeader("referer");
        response.sendRedirect(url);
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
