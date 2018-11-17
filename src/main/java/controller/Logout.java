package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
//        System.out.println("before destroying:"+session.getAttribute("user").toString());



        if(session!=null){
            session.invalidate();
        }
        //System.out.println("After destroying:"+session.getAttribute("user").toString());
//        RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
//        rd.forward(req,resp);
        response.sendRedirect("login");
    }
}
