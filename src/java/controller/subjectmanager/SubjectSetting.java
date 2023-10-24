/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.subjectmanager;

import dal.SubjectSettingDAO;
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
public class SubjectSetting extends HttpServlet {

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
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        int subjectID = Integer.parseInt(request.getParameter("subID"));
        request.setAttribute("subID", subjectID);
        SubjectSettingDAO subjectSettingDAO = new SubjectSettingDAO();
        if (action == null || action.trim().isEmpty()) {
            int pagenum = request.getParameter("pagenum") != null ? Integer.parseInt(request.getParameter("pagenum")) : 1;
            String searchValue = request.getParameter("search");
            request.setAttribute("search", searchValue);
            String status = request.getParameter("status");
            request.setAttribute("status", status);
            SubjectSettingDAO settingDAO = new SubjectSettingDAO();
            List<model.SubjectSetting> list = settingDAO.getSubjectSettings(subjectID, searchValue, status);
            List<model.SubjectSetting> pagination = Helper.pagination(list, pagenum, 5);
            int totalPage = list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1);
            request.setAttribute("pageNum", pagenum);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("settingList", pagination);
            request.setAttribute("active", 3);
            request.getRequestDispatcher("SubjectSetting.jsp").forward(request, response);
            return;
        } else if (action.equals("delete")) {
            subjectSettingDAO.deleteByID(Integer.parseInt(id));
        } else if (action.equals("update")) {
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String description = request.getParameter("description");
            String order = request.getParameter("order");
            String status = request.getParameter("status");
            subjectSettingDAO.updateSubjectSetting(Integer.parseInt(id), type, name, status.equals("1"), Integer.parseInt(order));
        } else if (action.equals("add")) {
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String description = request.getParameter("description");
            String order = request.getParameter("order");
            String status = request.getParameter("status");
            subjectSettingDAO.insert(type, name, status.equals("1"), description, subjectID, Integer.parseInt(order));
        }
        response.sendRedirect("SubjectSetting?subID=" + subjectID);
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
        processRequest(request, response);
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
