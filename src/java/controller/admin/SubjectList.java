/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import controller.*;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Subject;

/**
 *
 * @author phuc2
 */
@WebServlet(name = "SubjectList", urlPatterns = {"/subjectList"})
public class SubjectList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.length() == 0) {
            SubjectDAO s = new SubjectDAO();
            ArrayList<Subject> list1 = s.getAllSubject();
            int page, numberpage = 6;
            int size = list1.size();
            int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numberpage;
            end = Math.min(page * numberpage, size);
            List<Subject> list = s.getListByPage(list1, start, end);

            request.setAttribute("data2", list);
            request.setAttribute("page", page);
            request.setAttribute("num", num);

            request.getRequestDispatcher("manageSubject.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("add")) {
            request.getRequestDispatcher("addNewSubject.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("update")) {
            String id_raw = request.getParameter("id");
            int id = Integer.parseInt(id_raw);
            SubjectDAO sub = new SubjectDAO();
            Subject data3 = sub.getAllSubjectById(id);
            request.setAttribute("data3", data3);
            request.getRequestDispatcher("updateSubject.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("delete")) {
            String id_raw = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(id_raw);
                SubjectDAO s = new SubjectDAO();
                s.delete(id);
                response.sendRedirect("subjectList");
            } catch (NumberFormatException e) {
                response.sendRedirect("subjectList");
            }
        } else {
            response.sendRedirect("subjectList");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.length() == 0) {
            response.sendRedirect("subjectList");
        } else if (action.equalsIgnoreCase("add")) {
            String code = request.getParameter("subject_code");
            String name = request.getParameter("subject_name");
            String des = request.getParameter("subject_description");
            String status = request.getParameter("is_active");
            SubjectDAO sub = new SubjectDAO();
            sub.insertSubject(code, name, des, status);
            String nofi = "Create subject successfully!!!";
            request.setAttribute("nofi", nofi);
            request.getRequestDispatcher("addNewSubject.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("update")) {
            String id_raw = request.getParameter("id");
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String des = request.getParameter("des");
            String status = request.getParameter("status");
            int id = Integer.parseInt(id_raw);

            SubjectDAO sub = new SubjectDAO();

            sub.updateSubject(code, name, des, status, id);

            String mess = "Update successfully!!!";
            request.setAttribute("mess", mess);
            Subject data3 = sub.getAllSubjectById(id);
            request.setAttribute("data3", data3);
            request.getRequestDispatcher("updateSubject.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("delete")) {
            String id_raw = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(id_raw);
                SubjectDAO s = new SubjectDAO();
                s.delete(id);
                response.sendRedirect("subjectList");
            } catch (NumberFormatException e) {
                response.sendRedirect("subjectList");
            }
        } else {
            response.sendRedirect("subjectList");
        }
    }

}
