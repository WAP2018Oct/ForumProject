package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommentDB {
    private static Map<Integer, Comment> commentDB = new HashMap<>();

    static {
        commentDB.put(1, new Comment(1, Userdb.getUserById(1), "comment 1", 1));
        commentDB.put(2, new Comment(2, Userdb.getUserById(1), "comment 2", 1));
        commentDB.put(3, new Comment(3, Userdb.getUserById(1), "comment 3", 1));
        commentDB.put(4, new Comment(4, Userdb.getUserById(1), "comment 4", 1));
        commentDB.put(5, new Comment(5, Userdb.getUserById(1), "comment 1", 2));
        commentDB.put(6, new Comment(6, Userdb.getUserById(1), "comment 2", 2));
    }

    public static void addComment(Comment comment) {
        commentDB.put(comment.getId(), comment);
    }

    public static void deleteComment(int postId) {
        commentDB.remove(postId);
    }

    public static void updateComment(Comment comment) {
        commentDB.put(comment.getId(), comment);
    }

    public static List<Comment> getAllComments() {
        return new ArrayList<>(commentDB.values());
    }

    public static Comment getCommentById(int commentId) {
        return commentDB.get(commentId);
    }

    public static int genId() {
        return commentDB.size() + 1;
    }

    public List<Comment> getAllCommentsByPostId(int postId) {
        return new ArrayList<>(commentDB.values()).stream()
                .filter(comment -> comment.getPostId() == postId)
                .collect(Collectors.toList());
    }
}
