package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostDB {
    Map<Integer, Post> postDB = new HashMap<>();


    {
//        postDB.put(1, new Post(1, , "549"));
    }

    public void addPost(Post post) {
        postDB.put(post.getId(), post);
    }

    public void deletePost(int postId) {
        postDB.remove(postId);
    }

    public void updatePost(Post post) {
        postDB.put(post.getId(), post);
    }

    public List<Post> getAllPosts() {
        return new ArrayList<>(postDB.values());
    }

    public Post getPostById(int postId) {
        return postDB.get(postId);
    }

    public int genId() {
        return postDB.size() + 1;
    }
}
