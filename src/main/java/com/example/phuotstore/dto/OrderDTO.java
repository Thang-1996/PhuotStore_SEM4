package com.example.phuotstore.dto;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class OrderDTO {

    @NotNull
    private String orderName;

    private String note;


    private String status;

    private int totalQuantity;

    private double totalPrice;

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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
