/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.classmanager;

import dal.AssignmentDAO;
import dal.ClassDAO;
import dal.ClassSettingDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Assignment;
import model.Class;
import model.ClassIssueSetting;
import model.User;
import ulti.Helper;

/**
 *
 * @
 */
public class classDetail extends HttpServlet {

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
            String id = request.getParameter("classID");
            request.setAttribute("classID", id);
            ClassDAO classDAO = new ClassDAO();
            Class c = classDAO.getClassByID(Integer.parseInt(id));
            int num = classDAO.getNumberOfStudents(Integer.parseInt(id));
            request.setAttribute("num", num);
            request.setAttribute("cls", c);
            request.setAttribute("active", 1);
            request.getRequestDispatcher("ClassDetail.jsp").forward(request, response);
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

    private void setListAssignment(HttpServletRequest request, int classID) {
        int pagenum = request.getParameter("pageNumAssignment") != null ? Integer.parseInt(request.getParameter("pageNumAssignment")) : 1;
        String searchValue = request.getParameter("searchAss");
        request.setAttribute("searchAss", searchValue);
        String startDate = request.getParameter("startDate");
        request.setAttribute("startDate", startDate);
        String endDate = request.getParameter("endDate");
        request.setAttribute("endDate", endDate);
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        List<Assignment> list = assignmentDAO.getAssignmentByClass(searchValue, startDate, endDate, classID);
        List<Assignment> pagination = Helper.pagination(list, pagenum, 5);
        int totalPage = list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1);
        request.setAttribute("pageNumAssignment", pagenum);
        request.setAttribute("totalPageAss", totalPage);
        request.setAttribute("assignmentList", pagination);
    }

    private void setListClassSetting(HttpServletRequest request, int classID) {
        int pagenum = request.getParameter("pageNumSetting") != null ? Integer.parseInt(request.getParameter("pageNumAssignment")) : 1;
        String searchValue = request.getParameter("searchSetting");
        request.setAttribute("searchSetting", searchValue);
        String status = request.getParameter("statusSetting");
        request.setAttribute("statusSetting", status);
        ClassSettingDAO classSettingDAO = new ClassSettingDAO();
        List<ClassIssueSetting> list = classSettingDAO.getAll(searchValue, status, classID);
        List<ClassIssueSetting> pagination = Helper.pagination(list, pagenum, 5);
        int totalPage = list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1);
        request.setAttribute("pageNumSetting", pagenum);
        request.setAttribute("totalPageSetting", totalPage);
        request.setAttribute("settingList", pagination);
    }

    private void setStudentList(HttpServletRequest request, int subID) {
        int pagenum = request.getParameter("pageNumStudent") != null ? Integer.parseInt(request.getParameter("pageNumStudent")) : 1;
        String searchValue = request.getParameter("searchStudent");
        request.setAttribute("searchStudent", searchValue);
        UserDAO udao = new UserDAO();
        List<User> list = udao.getAllUserByClass(searchValue, subID);
        List<User> pagination = Helper.pagination(list, pagenum, 5);
        int totalPage = list.size() % 5 == 0 ? (list.size() / 5) : (list.size() / 5 + 1);
        request.setAttribute("pageNumStudents", pagenum);
        request.setAttribute("totalPageStudents", totalPage);
        request.setAttribute("users", pagination);
    }
}
