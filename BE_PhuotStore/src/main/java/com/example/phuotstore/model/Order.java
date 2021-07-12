package com.example.phuotstore.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @NotNull
    private String orderName;

    private String note;

    @CreationTimestamp
    private Date createAt;
    @CreationTimestamp
    private Date updateAt;
    @CreationTimestamp
    private Date endAt;

    @NotNull
    private String status;

    @NotNull(message = "Quantity must not be null")
    private int quantity;

    private double totalPrice;

    private String orderType;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "orderDetailProduct",
        joinColumns = @JoinColumn(name = "orderID"),
        inverseJoinColumns = @JoinColumn(name = "productID"))
    private Set<Product> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "orderDetailCombo",
        joinColumns = @JoinColumn(name = "orderID"),
        inverseJoinColumns = @JoinColumn(name = "comboID"))
    private Set<Combo> combos = new HashSet<>();

    public Order() {
    }

    public Order(int orderID, @NotNull String orderName, String note, Date createAt, Date updateAt, Date endAt, String status, @NotNull(message = "Quantity must not be null") int quantity, double totalPrice, String orderType, @NotNull User user, Set<Product> products, Set<Combo> combos) {
        this.orderID = orderID;
        this.orderName = orderName;
        this.note = note;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.endAt = endAt;
        this.status = status;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderType = orderType;
        this.user = user;
        this.products = products;
        this.combos = combos;
    }

    public Order(String orderName, String note, Date createAt, Date updateAt, String status, int quantity) {
        this.orderName = orderName;
        this.note = note;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
        this.quantity = quantity;
    }


    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Combo> getCombos() {
        return combos;
    }

    public void setCombos(Set<Combo> combos) {
        this.combos = combos;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
