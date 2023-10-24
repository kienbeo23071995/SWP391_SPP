/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.subjectmanager;

import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Subject;
import ulti.Helper;

/**
 *
 * @
 */
public class SubjectList extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubjectList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        SubjectDAO subjectDAO = new SubjectDAO();
        if (action == null || action.trim().isEmpty()) {
            int pagenum = request.getParameter("pagenum") != null ? Integer.parseInt(request.getParameter("pagenum")) : 1;
            String searchValue = request.getParameter("search");
            request.setAttribute("searchValue", searchValue);
            String status = request.getParameter("status");
            request.setAttribute("status", status);
            ArrayList<Subject> list = subjectDAO.getAllSubjectWithSearch(searchValue, status);
            List<Subject> pagination = Helper.pagination(list, pagenum, 5);
            int totalPage = list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1);
            request.setAttribute("pagenum", pagenum);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("subjects", pagination);
            request.getRequestDispatcher("SubjectManager.jsp").forward(request, response);
        } else if (action.equals("update")) {
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String status = request.getParameter("status");
            String description = request.getParameter("description");
            subjectDAO.updateSubject(code, name, description, status, Integer.parseInt(id));
            response.sendRedirect("subjectList");
        } else if (action.equals("delete")) {
            subjectDAO.delete(Integer.parseInt(id));
            response.sendRedirect("subjectList");
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
        doGet(request, response);
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
