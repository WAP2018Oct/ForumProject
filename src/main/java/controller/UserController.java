package controller;


import Model.User;
import Model.Userdb;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//@WebServlet({ "/user", "" })
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Userdb dao;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        dao = new Userdb();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("users", dao.getAllUserss());

        RequestDispatcher view = request.getRequestDispatcher("user.jsp");
        view.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Tesing if it works");
       // String requestMethod = request.getmethod();
        String jsonSting = request.getParameter("user");
        User user = mapper.readValue(jsonSting, User.class);
        user.setId(dao.genUniqueId());
        dao.addUsers(user);
        PrintWriter out =response.getWriter();
        try{
            out.print(mapper.writeValueAsString(user));
        }catch (JsonGenerationException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            System.out.println("Deleting...");
        //super.doDelete(req, resp);
        int userId = Integer.parseInt(request.getParameter("id"));
        dao.deleteUsers(userId);
        System.out.println("Working..");
        response.getWriter().println(userId);
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Updating...");
        String updateRole = request.getParameter("role");
        String updatePassword = request.getParameter("password");
        String updateUserName = request.getParameter("username");
        String updateFirstName = request.getParameter("firstName");
        String updateLastName = request.getParameter("lastName");
        int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println(updateFirstName+" "+updateLastName+" "+updateRole+""+updatePassword+""+updateUserName);
        for(User user:dao.getAllUserss()){
            if(user.getId()==userId){
                user.setFirstName(updateFirstName);
                user.setLastName(updateLastName);
                user.setPassword(updatePassword);
                user.setUsername(updateUserName);
                user.setRole(updateRole);
            }
        }
    }
}
