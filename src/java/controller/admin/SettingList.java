/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import controller.*;
import dal.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Setting;
import ulti.Helper;

/**
 *
 * @author SHD
 */
@WebServlet(name = "SettingList", urlPatterns = {"/settings"})
public class SettingList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.length() == 0) {
            int pageNum = request.getParameter("pageNum") != null ? Integer.parseInt(request.getParameter("pageNum")) : 1;
            String searchname = request.getParameter("name");
            String searchtype = request.getParameter("type");
            String status = request.getParameter("status");
            SettingDAO settingDAO = new SettingDAO();
            List<Setting> list = settingDAO.getList(searchname,searchtype,status);
            int totalPage = list.size() % 5 == 0? list.size() / 5 : (list.size()/5 + 1);
            List<Setting> paging = Helper.pagination(list, pageNum, 5);
            request.setAttribute("list", paging);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("typesearch", searchtype);
            request.setAttribute("namesearch", searchname);
            request.setAttribute("status", status);
            request.setAttribute("pagenum", pageNum);
            request.getRequestDispatcher("setting_list.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("add")) {
            request.getRequestDispatcher("addSetting.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("setting", new SettingDAO().getSetting(id));
            request.getRequestDispatcher("editSetting.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            new SettingDAO().deleteClass(id);
            new SettingDAO().delete(id);
        } else if (action.equalsIgnoreCase("view")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("setting", new SettingDAO().getSetting(id));
            request.getRequestDispatcher("viewSetting.jsp").forward(request, response);
        } else {
            int pageNum = request.getParameter("pageNum") != null ? Integer.parseInt(request.getParameter("pageNum")) : 1;
            String searchname = request.getParameter("name");
            String searchtype = request.getParameter("type");
            String status = request.getParameter("status");
            SettingDAO settingDAO = new SettingDAO();
            List<Setting> list = settingDAO.getList(searchname,searchtype,status);
            int totalPage = list.size() % 5 == 0? list.size() / 5 : (list.size()/5 + 1);
            List<Setting> paging = Helper.pagination(list, pageNum, 5);
            request.setAttribute("list", paging);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("typesearch", searchtype);
            request.setAttribute("namesearch", searchname);
            request.setAttribute("status", status);
            request.setAttribute("pagenum", pageNum);
            request.getRequestDispatcher("setting_list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null && action.length() == 0) {
            int pageNum = request.getParameter("pageNum") != null ? Integer.parseInt(request.getParameter("pageNum")) : 1;
            String searchname = request.getParameter("name");
            String searchtype = request.getParameter("type");
            String status = request.getParameter("status");
            SettingDAO settingDAO = new SettingDAO();
            List<Setting> list = settingDAO.getList(searchname,searchtype,status);
            int totalPage = list.size() % 5 == 0? list.size() / 5 : (list.size()/5 + 1);
            List<Setting> paging = Helper.pagination(list, pageNum, 5);
            request.setAttribute("list", paging);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("typesearch", searchtype);
            request.setAttribute("namesearch", searchname);
            request.setAttribute("status", status);
            request.setAttribute("pagenum", pageNum);
            request.getRequestDispatcher("setting_list.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("add")) {
            String type = request.getParameter("type");
            String name = request.getParameter("name");
            int is_active = Integer.parseInt(request.getParameter("is_active"));
            Setting s = new Setting(is_active, name, type);

            if (new SettingDAO().checkExist(type, name)) {
                request.setAttribute("setting", s);
                request.setAttribute("error", "Type : " + type + ",Name : " + name + " already exist");
            } else {
                new SettingDAO().add(type, name,is_active);
                request.setAttribute("success", "Add succesfully");
            }
            request.getRequestDispatcher("addSetting.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("update")) {
            int id = Integer.parseInt(request.getParameter("id"));

            String type = request.getParameter("type");
            String name = request.getParameter("name");
            int is_active = Integer.parseInt(request.getParameter("is_active"));
            new SettingDAO().update(type, name, is_active, id);
            request.setAttribute("setting", new SettingDAO().getSetting(id));
            request.setAttribute("success", "Update succesfully");
            request.getRequestDispatcher("editSetting.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            new SettingDAO().deleteClass(id);
            new SettingDAO().delete(id);
        } else if (action.equalsIgnoreCase("view")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("setting", new SettingDAO().getSetting(id));
            request.getRequestDispatcher("viewSetting.jsp").forward(request, response);
        } else {
            int pageNum = request.getParameter("pageNum") != null ? Integer.parseInt(request.getParameter("pageNum")) : 1;
            String searchname = request.getParameter("name");
            String searchtype = request.getParameter("type");
            String status = request.getParameter("status");
            SettingDAO settingDAO = new SettingDAO();
            List<Setting> list = settingDAO.getList(searchname,searchtype,status);
            int totalPage = list.size() % 5 == 0? list.size() / 5 : (list.size()/5 + 1);
            List<Setting> paging = Helper.pagination(list, pageNum, 5);
            request.setAttribute("list", paging);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("typesearch", searchtype);
            request.setAttribute("namesearch", searchname);
            request.setAttribute("status", status);
            request.setAttribute("pagenum", pageNum);
            request.getRequestDispatcher("setting_list.jsp").forward(request, response);
        }
    }

}
