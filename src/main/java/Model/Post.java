package Model;

import java.time.LocalDate;

public class Post {
    private User user;
    private String postContent;
    private LocalDate postedDate;
    private String postTitle;
    private int id;


    public Post(int id, User user, String posttitle, String postContent, LocalDate postedDate) {
        this.user = user;
        this.postContent = postContent;
        this.postedDate = postedDate;
        this.postTitle = posttitle;
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userdb) {
        this.user = userdb;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public LocalDate getPostedDate() {
        return postedDate;
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
