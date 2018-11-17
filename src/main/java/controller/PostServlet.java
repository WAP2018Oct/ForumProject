package controller;

import Model.Post;
import Model.PostDB;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PostServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int postId = 1;
        try {
            postId = Integer.parseInt(pathParts[1]);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        Post post = PostDB.getPostById(postId);

        PrintWriter out =resp.getWriter();
        out.print(mapper.writeValueAsString(post));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
