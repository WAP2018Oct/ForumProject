package Model;

import java.time.LocalDateTime;

public class Comment implements Cloneable {
    private int id;
    private User author;
    private String comment;
    private LocalDateTime createdDate;
    private int postId;

    public Comment(int id, User author, String comment, LocalDateTime createdDate, int postId) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.createdDate = createdDate;
        this.postId = postId;
    }

    public Comment(int id, User author, String comment, int postId) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.postId = postId;
        this.createdDate = LocalDateTime.now();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
