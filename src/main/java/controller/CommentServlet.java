package controller;

import Model.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class CommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CommentDB dao;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        dao = new CommentDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int post_id = 1;
        req.setAttribute("comments", dao.getAllCommentsByPostId(post_id));
        RequestDispatcher view = req.getRequestDispatcher("comment.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("Post is working..");
        int postId = Integer.parseInt(req.getParameter("post_id"));
        String commentText = req.getParameter("comment");
        /*GET USER FROM SESSION DATA*/
        User tempUser = Userdb.getUserById(1); // temp user;
        if (tempUser.getRole().equals("Admin") ||
                (tempUser.getRole().equals("Contributor") && PostDB.getPostById(postId).getUser() == tempUser)) {
            Comment comment = new Comment(dao.genId(), tempUser, commentText, LocalDateTime.now(), postId);
            dao.addComment(comment);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Update is working..");
        /*GET USER FROM SESSION DATA*/
        User tempUser = Userdb.getUserById(1); // temp user;
        int postId = Integer.parseInt(req.getParameter("post_id"));
        if (tempUser.getRole().equals("Admin") ||
                (tempUser.getRole().equals("Contributor") && PostDB.getPostById(postId).getUser() == tempUser)) {
            int commentId = Integer.parseInt(req.getParameter("id"));
            Comment comment = dao.getCommentById(commentId);
            String commentText = req.getParameter("comment");
            comment.setComment(commentText);
            dao.updateComment(comment);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Delete is working..");
        /*GET USER FROM SESSION DATA*/
        User tempUser = Userdb.getUserById(1); // temp user;
        int postId = Integer.parseInt(req.getParameter("post_id"));
        if (tempUser.getRole().equals("Admin") ||
                (tempUser.getRole().equals("Contributor") && PostDB.getPostById(postId).getUser() == tempUser)) {
            int commentId = Integer.parseInt(req.getParameter("id"));
            dao.deleteComment(commentId);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
