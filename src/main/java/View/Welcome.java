package View;

import Model.User;
import Model.Userdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean flag = (request.getParameter("rememberme") != null ? true : false);
//        User user = new User(username, password, flag);
        User user = Userdb.getUserById(1);
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("user", user);
        Cookie cookie = new Cookie("Name", username);
        if (request.getParameter("rememberme") != null) {
            System.out.println("Yes Cookie");
            cookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(cookie);
        } else {
            System.out.println("No Cookie");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        PrintWriter out = response.getWriter();

        out.print("<html><head><title>ViewPage</title></head>");
        out.print("<body><p>Welcome " + username);
        out.print("<input type='text' value=" + username + " id=" + username + "/>");
        out.print("</p>");
        out.print("<form action='Logout' method='GET'>");
        out.print("<input type='submit' value='Logout' />");
        out.print("</form></body></html>");
        out.print("<form action='AddPost' method='GET'>");
        out.print("<input type='submit' value='Add New Post' />");
        out.print("</form></body></html>");
        out.print("<form action='/usermngt.jsp' method='GET'>");
        out.print("<input type='submit' value='UserMangnt' />");
        out.print("</form></body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
