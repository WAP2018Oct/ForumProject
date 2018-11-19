package controller;

import Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//        User currentUser = (User) req.getSession().getAttribute("user");

//        comments = comments.stream().map(comment -> {
//            if (currentUser != null && comment.getAuthor().getId() != currentUser.getId()) {
//                comment.setAuthor(null);
//            }
//            return comment;
//        }).collect(Collectors.toList());

        PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(new PostComment(post, comments)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String fromForm = req.getParameter("fromForm");

        /*GET USER FROM SESSION DATA*/
        User tempUser = (User) req.getSession().getAttribute("user");

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
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String data = br.readLine();

        String[] parameterStrings = data.split("&");
        Map<String, String> parameterMap = new HashMap<>();
        for (String parameterString : parameterStrings) {
            String parameterName = parameterString.substring(0, parameterString.indexOf('='));
            String parameterValue = parameterString.substring(parameterString.indexOf('=') + 1);

            parameterMap.put(parameterName, URLDecoder.decode(parameterValue, StandardCharsets.UTF_8));
        }

        String title = parameterMap.get("title");
        String description = parameterMap.get("description");

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
        User tempUser = (User) req.getSession().getAttribute("user");

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
        User tempUser = (User) req.getSession().getAttribute("user");

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
