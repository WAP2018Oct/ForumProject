package controller;

import Model.CommentDB;
import Model.Post;
import Model.PostDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            req.setAttribute("isLoggedIn", true);
        } else {
            req.setAttribute("isLoggedIn", false);
        }

        Map<Integer, Integer> commentMap = new HashMap<>(); //CommentCount

        List<Post> last10Posts = PostDB.getLastNPost(10);

        for (Post post : last10Posts) {
            commentMap.put(post.getId(), CommentDB.getAllCommentsByPostId(post.getId()).size());
            if (post.getPostContent().length() > 180) {
                post.setPostContent(post.getPostContent().substring(0, 180) + "...");
            }
        }

        req.setAttribute("posts", last10Posts);
        req.setAttribute("commentMap", commentMap);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
        dispatcher.forward(req, resp);
    }
}
