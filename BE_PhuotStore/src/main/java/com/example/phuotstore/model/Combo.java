package com.example.phuotstore.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comboID;

    @NotNull
    private String comboName;

    @NotNull
    private String comboCode;

    @NotNull
    private String comboDesc;

    private int discount;

    @NotNull(message = "Quantity must not be null")
    private int quantity;

    @NotNull(message = "Price must not be null")
    private double totalPrice;
    @NotNull
    private String status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "comboDetail",
        joinColumns = @JoinColumn(name = "comboID"),
        inverseJoinColumns = @JoinColumn(name = "productID"))
    private Set<Product> products = new HashSet<>();

    public Combo() {
    }

    public Combo(String comboName, String comboCode, String comboDesc, int discount, int quantity, String status) {
        this.comboName = comboName;
        this.comboCode = comboCode;
        this.comboDesc = comboDesc;
        this.discount = discount;
        this.quantity = quantity;
        this.status = status;
    }

    public int getComboID() {
        return comboID;
    }

    public void setComboID(int comboID) {
        this.comboID = comboID;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getComboCode() {
        return comboCode;
    }

    public void setComboCode(String comboCode) {
        this.comboCode = comboCode;
    }

    public String getComboDesc() {
        return comboDesc;
    }

    public void setComboDesc(String comboDesc) {
        this.comboDesc = comboDesc;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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
