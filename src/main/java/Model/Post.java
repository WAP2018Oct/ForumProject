package Model;

import java.time.LocalDate;

public class Post {
    private Userdb userdb;
    private String postContent;
    private LocalDate postedDate;
    private String postTitle;
    private int id;


    public Post(Userdb userdb, String posttitle, String postContent, LocalDate postedDate, int id) {
        this.userdb = userdb;
        this.postContent = postContent;
        this.postedDate = postedDate;
        this.postTitle = posttitle;
        this.id = id;
    }

    public Userdb getUserdb() {
        return userdb;
    }

    public String getPostContent() {
        return postContent;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setUser(Userdb userdb) {
        this.userdb = userdb;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
