package controller;

import Model.Comment;
import Model.CommentDB;
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
import java.time.LocalDate;

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

        int postId = Integer.parseInt(req.getParameter("post_id"));
        System.out.println(req.getParameter("comment"));
        String commentText = req.getParameter("comment");
        /*GET USER FROM SESSION DATA*/
        User tempUser = Userdb.getUserById(1); // temp user;
        //Comment comment = mapper.readValue(req.getParameter("comment"), Comment.class);
        Comment comment = new Comment(dao.genId(), tempUser, commentText, LocalDate.now(), postId);
        //comment.setId(dao.genId());
        dao.addComment(comment);
        System.out.println(postId);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("Update Working..");
        int commentId = Integer.parseInt(req.getParameter("id"));
        Comment comment = dao.getCommentById(commentId);
        String commentText = req.getParameter("comment");
        //System.out.println(commentText);
        comment.setComment(commentText);
        dao.updateComment(comment);
        //resp.getWriter().println(commentId);
        //resp.getWriter().println(commentText);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        int commentId = Integer.parseInt(req.getParameter("id"));
        dao.deleteComment(commentId);
        //System.out.println("Working..");
        //resp.getWriter().println(commentId);

    }
}
