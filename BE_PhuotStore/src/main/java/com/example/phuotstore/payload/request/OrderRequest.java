package com.example.phuotstore.payload.request;

import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

public class OrderRequest {

    @NotNull
    private String orderName;

    private String note;

    @CreationTimestamp
    private Date createAt;
    @CreationTimestamp
    private Date updateAt;

    private String status;

    @NotNull(message = "Quantity must not be null")
    private int quantity;

    private Integer userID;

    private Set<Integer> product;

    private Set<Integer> combo;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Set<Integer> getProduct() {
        return product;
    }

    public void setProduct(Set<Integer> product) {
        this.product = product;
    }

    public Set<Integer> getCombo() {
        return combo;
    }

    public void setCombo(Set<Integer> combo) {
        this.combo = combo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
