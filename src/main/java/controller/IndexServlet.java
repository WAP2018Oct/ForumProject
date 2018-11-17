package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("TEST:" + req.getSession().getAttribute("username"));

        if(req.getSession().getAttribute("username") != null) {
            req.setAttribute("isLoggedIn", true);
        } else {
            req.setAttribute("isLoggedIn",false );
        }

        System.out.println(req.getAttribute("isLoggedIn"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
        dispatcher.forward(req, resp);
    }
}
