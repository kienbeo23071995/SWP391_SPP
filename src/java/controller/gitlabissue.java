/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import ulti.RestAPI;

/**
 *
 * @author kienb
 */
@WebServlet(name = "gitlabissue", urlPatterns = {"/gitlabissue"})
public class gitlabissue extends HttpServlet {

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
            JSONArray jsonArray = RestAPI.getJsonArray("projects", "49126518", "issues", "glpat-Gw-3fHH1o_Svvbe1XgRb");
            List<String> issues_iids = new ArrayList<>();
            List<String> issues_titles = new ArrayList<>();
            List<String> issues_descriptions = new ArrayList<>();
            List<String> issues_gitlab_id = new ArrayList<>();
            List<String> issues_gitlab_url = new ArrayList<>();
            List<String> issues_created_date = new ArrayList<>();
            List<String> issues_due_date = new ArrayList<>();
            List<String> issues_team_id = new ArrayList<>();
            List<String> issues_milestone_id = new ArrayList<>();
            List<String> issues_function_ids = new ArrayList<>();
            List<String> issues_labels = new ArrayList<>();
            List<String> issues_status = new ArrayList<>();
            for (int j = 10; j > 0; --j) {
                int warring = 0;
                String values = String.valueOf(j);
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);

                        issues_iids.add(json.get("iid").toString().trim());
                        issues_titles.add(json.get("title").toString().trim());
                        issues_descriptions.add(json.get("description").toString().trim());
                        issues_gitlab_id.add(json.get("project_id").toString().trim());
                        issues_gitlab_url.add(json.get("web_url").toString().trim());
                        issues_created_date.add(json.get("created_at").toString().trim().substring(0, 10));
                        issues_due_date.add(json.get("updated_at").toString().trim().substring(0, 10));
                        issues_labels.add(json.get("labels").toString().trim());
                        issues_status.add(json.get("state").toString().trim());
                    }
                }
            }
            for (int i = 0; i < issues_iids.size(); i++) {
                System.out.println(issues_iids.get(i));
                System.out.println(issues_titles.get(i));
                System.out.println(issues_descriptions.get(i));
                System.out.println(issues_gitlab_id.get(i));
                System.out.println(issues_gitlab_url.get(i));
                System.out.println(issues_created_date.get(i));
                System.out.println(issues_labels.get(i).contains("Doing"));
                if (issues_labels.get(i).contains("Doing")) {
                    System.out.println(issues_labels.get(i));
                }
            }

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
