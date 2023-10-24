/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.classmanager;

import dal.ClassSettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ClassIssueSetting;
import ulti.Helper;

/**
 *
 * @
 */
public class classIssuseSetting extends HttpServlet {

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
            String id = request.getParameter("id");
            String action = request.getParameter("action");
            String classID = request.getParameter("classID");
            ClassSettingDAO classSettingDAO = new ClassSettingDAO();
            request.setAttribute("classID", classID);
            if (action == null || action.trim().isEmpty()) {
                int pagenum = request.getParameter("pageNum") != null ? Integer.parseInt(request.getParameter("pageNumAssignment")) : 1;
                String searchValue = request.getParameter("search");
                request.setAttribute("search", searchValue);
                String status = request.getParameter("status");
                request.setAttribute("status", status);         
                List<ClassIssueSetting> list = classSettingDAO.getAll(searchValue, status, Integer.parseInt(classID));
                List<ClassIssueSetting> pagination = Helper.pagination(list, pagenum, 5);
                int totalPage = list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1);
                request.setAttribute("pageNum", pagenum);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("settingList", pagination);
                request.setAttribute("active", 3);
                request.getRequestDispatcher("ClassIssueSetting.jsp").forward(request, response);
                return;
            }
            else if(action.equals("delete")){
                classSettingDAO.deleteByID(Integer.parseInt(id));
            }
            else if(action.equals("update")){
                String type = request.getParameter("type");
                String workprocess = request.getParameter("workprocess");
                String status = request.getParameter("status");
                classSettingDAO.update(id, type, status.equals("1"), workprocess, Integer.parseInt(classID));             
            }
            else if(action.equals("add")) {
                String type = request.getParameter("type");
                String workprocess = request.getParameter("workprocess");
                String status = request.getParameter("status");
                classSettingDAO.insert(type, status.equals("1"), workprocess,Integer.parseInt(classID));
            }
            response.sendRedirect("classIssuseSetting?classID=" + classID);
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
