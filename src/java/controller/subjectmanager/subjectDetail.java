/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.subjectmanager;

import dal.ClassDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import ulti.Helper;

/**
 *
 * @
 */
public class subjectDetail extends HttpServlet {

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
            out.println("<title>Servlet subjectDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet subjectDetail at " + request.getContextPath() + "</h1>");
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
        int subjectID = Integer.parseInt(request.getParameter("subID"));
        request.setAttribute("subID", subjectID);
        ClassDAO classDAO = new ClassDAO();
        if (action == null || action.trim().isEmpty()) {
            int pagenum = request.getParameter("pagenum") != null ? Integer.parseInt(request.getParameter("pagenum")) : 1;
            String searchValue = request.getParameter("search");
            request.setAttribute("search", searchValue);
            String status = request.getParameter("status");
            request.setAttribute("status", status);
            List<model.Class> list = classDAO.getAll(searchValue, status, subjectID);
            List<model.Class> pagination = Helper.pagination(list, pagenum, 5);
            int totalPage = list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1);
            request.setAttribute("pagenum", pagenum);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("classes", pagination);
            request.setAttribute("active", 1);
            request.getRequestDispatcher("SubjectDetail.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            classDAO.deleteByID(Integer.parseInt(id));
            response.sendRedirect("subjectDetail?subID=" + subjectID);
        } else if (action.equals("update")) {
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String status = request.getParameter("status");
            classDAO.updateClass(Integer.parseInt(id), code, name, status, subjectID);
            response.sendRedirect("subjectDetail?subID=" + subjectID);
        } else if (action.equals("add")) {
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String status = request.getParameter("status");
            classDAO.insertClass(code, name, status, subjectID);
            response.sendRedirect("subjectDetail?subID=" + subjectID);
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
