package com.example.phuotstore.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    @NotNull
    private String commentName;

    private String commentInfo;

    @CreationTimestamp
    private Date commentDate;

    @NotNull
    private String status;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "productID", referencedColumnName = "productID")
    private Product product;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    public Comment() {
    }

    public Comment(int commentID, @NotNull String commentName, String commentInfo, Date commentDate, String status, @NotNull Product product, @NotNull User user) {
        this.commentID = commentID;
        this.commentName = commentName;
        this.commentInfo = commentInfo;
        this.commentDate = commentDate;
        this.status = status;
        this.product = product;
        this.user = user;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
