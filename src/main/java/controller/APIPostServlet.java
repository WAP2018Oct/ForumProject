package controller;

import Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class APIPostServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int postId = 0;
        try {
            postId = Integer.parseInt(pathParts[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        Post post = PostDB.getPostById(postId);
        List<Comment> comments = CommentDB.getAllCommentsByPostId(postId);

        PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(new PostComment(post, comments)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String fromForm = req.getParameter("fromForm");

        System.out.println(fromForm);

        /*GET USER FROM SESSION DATA*/
        User tempUser = Userdb.getUserById(1); // temp user;

        Post newPost = new Post(PostDB.genId(), tempUser, title, description);

        PostDB.addPost(newPost);
        if (fromForm != null) {
            resp.sendRedirect("index.jsp");
        } else {
            PrintWriter out = resp.getWriter();
            out.print(mapper.writeValueAsString(newPost));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int postId = 0;
        try {
            postId = Integer.parseInt(pathParts[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        /*GET USER FROM SESSION DATA*/
        User tempUser = Userdb.getUserById(1); // temp user;

        Post newPost = new Post(postId, tempUser, title, description);
        PostDB.updatePost(newPost);

        PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(newPost));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int postId = 0;
        try {
            postId = Integer.parseInt(pathParts[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        /*GET USER FROM SESSION DATA*/
        User tempUser = Userdb.getUserById(1); // temp user;

        if (tempUser.getRole().equals("ADMIN") || PostDB.getPostById(postId).getUser() == tempUser) {
            Post deletedPost = PostDB.getPostById(postId);
            PostDB.deletePost(postId);
            PrintWriter out = resp.getWriter();
            out.print(mapper.writeValueAsString(deletedPost));
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
