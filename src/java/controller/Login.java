/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static controller.LoginGoogle.getToken;
import static controller.LoginGoogle.getUserInfo;
import dal.MD5;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Constants;
import model.GoogleDTO;
import model.User;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.length() == 0) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("loginPhone")) {
            if (request.getParameter("phone") != null) {
                User user = new UserDAO().getUserByPhone(request.getParameter("phone"));
                request.getSession().setAttribute("user", user);
                System.out.println(user.getFullname());
                response.sendRedirect("dashbroad1.jsp");
            } else {
                request.getRequestDispatcher("loginPhone.jsp").forward(request, response);
            }
        } else if (action.equalsIgnoreCase("loginGoogle")) {
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            GoogleDTO userGG = getUserInfo(accessToken);
            if (new UserDAO().checkExist(userGG.getEmail())) {
                String email = userGG.getEmail();
                User user = new UserDAO().getUser(email);
                request.getSession().setAttribute("user", user);
                System.out.println(user.getFullname());
                response.sendRedirect("dashbroad1.jsp");

            } else {
                request.setAttribute("error", "Yor email not exist or not verify in the system!Please choose another email");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.length() == 0) {
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = dao.chech(email, MD5.getMd5(password));

            if (user == null) {
                request.setAttribute("error", "Wrong information or account have not verify!");
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                //co tim thay
                session.setAttribute("user", user);
                request.getRequestDispatcher("dashbroad1.jsp").forward(request, response);

            }
        } else if (action.equalsIgnoreCase("loginPhone")) {
            String phone = request.getParameter("phone");

            if (new UserDAO().checkExistPhone(phone)) {
                request.setAttribute("success", "Phone exist");
            } else {

                request.setAttribute("erorr", "Phone not exist");
            }
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("loginPhone.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("loginGoogle")) {
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            GoogleDTO userGG = getUserInfo(accessToken);

            if (new UserDAO().checkExist(userGG.getEmail())) {
                String email = userGG.getEmail();
                User user = new UserDAO().getUser(email);
                request.getSession().setAttribute("user", user);
                System.out.println(user.getFullname());
                response.sendRedirect("dashbroad1.jsp");

            } else {
                request.setAttribute("error", "Yor email not exist or not verify in the system!Please choose another email");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static GoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        GoogleDTO googlePojo = new Gson().fromJson(response, GoogleDTO.class);

        return googlePojo;
    }

}
