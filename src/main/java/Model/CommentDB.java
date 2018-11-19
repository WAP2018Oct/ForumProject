package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommentDB {
    private static Map<Integer, Comment> commentDB = new HashMap<>();
    private static int id = 6;

    static {
        commentDB.put(1, new Comment(1, Userdb.getUserById(1), "comment 1\ntest", 1));
        commentDB.put(2, new Comment(2, Userdb.getUserById(2), "comment 2", 1));
        commentDB.put(3, new Comment(3, Userdb.getUserById(1), "comment 3", 1));
        commentDB.put(4, new Comment(4, Userdb.getUserById(3), "comment 4", 1));
        commentDB.put(5, new Comment(5, Userdb.getUserById(1), "comment 11\ntest", 2));
        commentDB.put(6, new Comment(6, Userdb.getUserById(1), "comment 22", 2));
        commentDB.put(7, new Comment(7, Userdb.getUserById(2), "comment 111", 2));
        commentDB.put(8, new Comment(8, Userdb.getUserById(3), "comment 222", 2));
    }

    public static void addComment(Comment comment) {
        commentDB.put(comment.getId(), comment);
    }

    public static void deleteComment(int commentId) {
        commentDB.remove(commentId);
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
        return ++id;
    }

    public static List<Comment> getAllCommentsByPostId(int postId) {
        return new ArrayList<>(commentDB.values()).stream()
                .filter(comment -> comment.getPostId() == postId)
                .map(comment -> {
                    try {
                        return (Comment) comment.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                        return comment;
                    }
                })
                .collect(Collectors.toList());
    }
}
