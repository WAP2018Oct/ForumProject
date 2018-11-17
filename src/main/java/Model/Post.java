package Model;

import java.time.LocalDateTime;

public class Post {
    private User user;
    private String postContent;
    private LocalDateTime postedDate;
    private String postTitle;
    private int id;


    public Post(int id, User user, String posttitle, String postContent, LocalDateTime postedDate) {
        this.user = user;
        this.postContent = postContent;
        this.postedDate = postedDate;
        this.postTitle = posttitle;
        this.id = id;
    }

    public Post(int id, User user, String posttitle, String postContent) {
        this.user = user;
        this.postContent = postContent;
        this.postedDate = LocalDateTime.now();
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

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
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
