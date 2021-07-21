package com.example.phuotstore.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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

    @NotNull(message = "Order Name must not be null")
    private String orderName;



    @CreationTimestamp
    private Date createAt;

    @CreationTimestamp
    private Date updateAt;

    @NotNull(message = "Status must not be null")
    private String status;

    private int totalQuantity;

    private double totalPrice;

    private String note;
    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private String phone;
    private String paymentType;


    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderDetailProduct",
        joinColumns = @JoinColumn(name = "orderID"),
        inverseJoinColumns = @JoinColumn(name = "productID"))
    private Set<Product> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderDetailCombo",
        joinColumns = @JoinColumn(name = "orderID"),
        inverseJoinColumns = @JoinColumn(name = "comboID"))
    private Set<Combo> combos = new HashSet<>();

    public Order() {
    }

    public Order(String orderName, String note, String status, int totalQuantity, double totalPrice, String firstName, String lastName, String email, String shippingAddress, String phone, String paymentType) {
        this.orderName = orderName;
        this.note = note;
        this.status = status;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.firstName= firstName;
        this.lastName= lastName;
        this.email= email;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        this.paymentType = paymentType;
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
        return totalQuantity;
    }

    public void setQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
