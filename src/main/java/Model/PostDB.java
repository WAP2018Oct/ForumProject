package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostDB {
    List<Post> postdb = new ArrayList<Post>();
    User user;

    PostDB(){

        postdb.add(new Post(new Userdb(new User("dbrown","123", "Dan","Brown","Admin",111)), "First Post", "This is our first post.", LocalDate.now(),10));
        postdb.add(new Post(new Userdb(new User("rwallace","1234", "Randal","Wallace","Author",112)), "First Post", "This is our first post.", LocalDate.now(),10));
    }

    public List<Post> getPostdb() {
        return postdb;
    }
}
