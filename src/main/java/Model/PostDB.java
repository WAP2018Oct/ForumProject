package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostDB {
    private static Map<Integer, Post> postDB = new HashMap<>();

    static {
        postDB.put(1, new Post(1, Userdb.getUserById(1), "Title",
                "Lorem ipsum dolor sit amet consectetur adipisicing elit. Unde, velit officia. Magnam, provident et autem necessitatibus repellendus enim, minima maiores magni tempore doloremque pariatur illum veritatis? Impedit quisquam debitis libero!",
                LocalDate.now()));
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
        return new ArrayList<>(postDB.values());
    }

    public static Post getPostById(int postId) {
        return postDB.get(postId);
    }

    public static int genId() {
        return postDB.size() + 1;
    }
}
