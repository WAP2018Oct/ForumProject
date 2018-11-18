package controller;

import Model.User;
import Model.Userdb;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("rememberme");
        boolean authenticated = false;
        HttpSession session = request.getSession();
        List<User> users = Userdb.getAllUsers();
        for (User usr : users) {
            if(usr.getUsername().equals(userName) && usr.getPassword().equals(password)) {
                authenticated = true;
                String fullName = usr.getFirstName()+ " "+usr.getLastName();
                session.setAttribute("user_info", fullName);

                if ("yes".equals(remember)) {
                    Cookie c = new Cookie("user", userName);
                    c.setMaxAge(30 * 24 * 60 * 60);
                    response.addCookie(c);
                } else {
                    Cookie c = new Cookie("user", null);
                    c.setMaxAge(0);
                    response.addCookie(c);

                }
            }
        }
        if (authenticated){ response.sendRedirect("welcome.jsp");
        } else {
            session.setAttribute("err_msg", "Username and/or password invalid.");
            response.sendRedirect("login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);


    }
}
