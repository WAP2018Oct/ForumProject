package controller;

import Model.Comment;
import Model.CommentDB;
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
        Comment comment = mapper.readValue(req.getParameter("comment"), Comment.class);
        comment.setId(dao.genId());
        dao.addComment(comment);

        PrintWriter out =resp.getWriter();
        try{
            out.print(mapper.writeValueAsString(comment));
        }catch (JsonGenerationException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
