package Model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PostDB {
    private static Map<Integer, Post> postDB = new HashMap<>();

    private static int id = 3;

    static {
        postDB.put(1, new Post(1, Userdb.getUserById(1), "Title",
                "Lorem ipsum dolor sit amet consectetur adipisicing elit. Unde, velit officia. Magnam, provident et autem necessitatibus repellendus enim, minima maiores magni tempore doloremque pariatur illum veritatis? Impedit quisquam debitis libero!",
                LocalDateTime.now().minusDays(2)));
        postDB.put(2, new Post(2, Userdb.getUserById(2), "POST 2",
                "THIS IS SECOND POST",
                LocalDateTime.now().minusDays(1)));
        postDB.put(3, new Post(3, Userdb.getUserById(3), "POST 3",
                "THIS IS THIRD POST",
                LocalDateTime.now()));
    }

    public static void addPost(Post post) {
        postDB.put(post.getId(), post);
    }

    public static void deletePost(int postId) {
        postDB.remove(postId);
    }

    public static void updatePost(Post post) {
        postDB.put(post.getId(), post);
    }

    public static List<Post> getAllPosts() {
        return new ArrayList<>(postDB.values()).stream()
                .sorted(Comparator.comparing(Post::getPostedDate).reversed())
                .map(post -> {
                    try {
                        return (Post) post.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                        return post;
                    }
                })
                .collect(Collectors.toList());
    }

    public static List<Post> getLastNPost(int limit) {
        return new ArrayList<>(postDB.values()).stream()
                .sorted(Comparator.comparing(Post::getPostedDate).reversed())
                .limit(limit)
                .map(post -> {
                    try {
                        return (Post) post.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                        return post;
                    }
                })
                .collect(Collectors.toList());
    }

    public static Post getPostById(int postId) {
        return postDB.get(postId);
    }

    public static int genId() {
        return ++id;
    }
}
