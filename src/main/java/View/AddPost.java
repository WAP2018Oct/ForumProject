package View;

import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddPost")
public class AddPost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("Shazam!");
        response.setContentType("text/html");
        System.out.println("Man");
        //HttpSession session = request.getSession();
        //String aa = (String )request.getSession().getAttribute("username");
        //System.out.println(aa+" WorkingSSSS");
        //String username = request.getParameter("username");
        //String password = request.getParameter("password");
        User user = (User)request.getSession().getAttribute("user");
        System.out.println(user.getUsername()+" Just Checking "+user.getPassword());
        boolean flag = (request.getParameter("rememberme")!=null?true:false);
        //User user= new User(username,password,flag);
        //HttpSession session = request.getSession();
        //session.setAttribute("user",user);
        PrintWriter out = response.getWriter();

        out.print("<html><head><title>ViewPage</title></head>");
        out.print("<body><p><h1>Add Post </h1>");
        out.print("</p>");
        out.print("<h2>Title</h2>");
        out.print("<input type='text' />");
        out.print("<h2>Post Description</h2>");
        out.print("<textarea cols='20' rows='10'></textarea>");
        out.print("<form action='Submit' method='POST'>");
        out.print("<input type='submit' value='Submit' />");
        out.print("</form></body></html>");
    }
}
