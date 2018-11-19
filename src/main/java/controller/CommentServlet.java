package controller;

import Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class CommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int post_id = 1;
        req.setAttribute("comments", CommentDB.getAllCommentsByPostId(post_id));
        RequestDispatcher view = req.getRequestDispatcher("comment.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        System.out.println("Post is working..");
        int postId = Integer.parseInt(req.getParameter("post_id"));
        String commentText = req.getParameter("comment");
        int userId = Integer.parseInt(req.getSession().getAttribute("user_id").toString());
        User user = Userdb.getUserById(userId); // temp user;
        if (user.getRole().equals("Admin") ||
                (user.getRole().equals("Contributor") &&
                        PostDB.getPostById(postId).getUser().getId() == user.getId())) {
            Comment comment = new Comment(CommentDB.genId(), user, commentText, LocalDateTime.now(), postId);
            CommentDB.addComment(comment);
            PrintWriter out = resp.getWriter();
            out.print(mapper.writeValueAsString(comment));
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Update is working..");
        /*GET USER FROM SESSION DATA*/
        int userId = Integer.parseInt(req.getSession().getAttribute("user_id").toString());
        User user = Userdb.getUserById(userId); // temp user;
        int postId = Integer.parseInt(req.getParameter("post_id"));
        if (user.getRole().equals("Admin") ||
                (user.getRole().equals("Contributor") &&
                        PostDB.getPostById(postId).getUser().getId() == user.getId())) {
            int commentId = Integer.parseInt(req.getParameter("id"));
            Comment comment = CommentDB.getCommentById(commentId);
            String commentText = req.getParameter("comment");
            comment.setComment(commentText);
            CommentDB.updateComment(comment);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Delete is working..");
        /*GET USER FROM SESSION DATA*/
        int userId = Integer.parseInt(req.getSession().getAttribute("user_id").toString());
        User user = Userdb.getUserById(userId); // temp user;
        int postId = Integer.parseInt(req.getParameter("post_id"));
        if (user.getRole().equals("Admin") ||
                (user.getRole().equals("Contributor") &&
                        PostDB.getPostById(postId).getUser().getId() == user.getId())) {
            int commentId = Integer.parseInt(req.getParameter("id"));
            CommentDB.deleteComment(commentId);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
