/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.classmanager;

import dal.GroupDAO;
import dal.ProjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Group;
import model.Project;
import ulti.Helper;

/**
 *
 * @author kienb
 */
public class projectList extends HttpServlet {

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
            String action = request.getParameter("action");           
            if (action == null || action.trim().isEmpty()) {
                int pagenum = request.getParameter("pagenum") != null ? Integer.parseInt(request.getParameter("pagenum")) : 1;
                String search = request.getParameter("search");
                request.setAttribute("search", search);
                String status = request.getParameter("status");
                request.setAttribute("status", status);
                String groupID = request.getParameter("groupID");
                request.setAttribute("groupID", groupID);
                GroupDAO groupDAO = new GroupDAO();
                List<Group> groups = groupDAO.getAll(null);
                ProjectDAO pdao = new ProjectDAO();
                List<Project> list = pdao.getAll(search, status, groupID);
                List<Project> pagination = Helper.pagination(list, pagenum, 5);
                int totalPage = list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1);
                request.setAttribute("groups", groups);
                request.setAttribute("pagenum", pagenum);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("projects", pagination);
                
                request.getRequestDispatcher("ListProject.jsp").forward(request, response);
                return;
            }
            else if(action.equals("update")){
                String id = request.getParameter("id");
                String engname = request.getParameter("englishname");
                String viname = request.getParameter("viname");
                String groupID = request.getParameter("groupID");
                String description = request.getParameter("description");
                ProjectDAO projectDAO = new ProjectDAO();
                projectDAO.updateProject(id, engname, viname, description, Integer.parseInt(groupID));
            }
            else if(action.equals("add")){
                String code = request.getParameter("code");
                String engname = request.getParameter("englishname");
                String viname = request.getParameter("viname");
                String groupID = request.getParameter("groupID");
                String description = request.getParameter("description");
                ProjectDAO projectDAO = new ProjectDAO();
                projectDAO.insertProject(code, engname, viname, description, Integer.parseInt(groupID));
            }
            response.sendRedirect("projectList");
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
