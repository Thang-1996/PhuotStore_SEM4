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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    @NotNull
    private String productName;

    @NotNull
    private String productCode;

    @NotNull
    private String productIMG;

    private String productDesc;

    private int discount;

    @Min(value = 1, message = "please chose a status")
    private int status;

    @NotNull
    private int qty;

    @NotNull
    private double price;

    @CreationTimestamp
    private Date createAt;
    @CreationTimestamp
    private Date updateAt;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "brandID", referencedColumnName = "brandID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Brand brand;

    public Product() {
    }

    public Product(int productID, @NotNull String productName, @NotNull String productCode, @NotNull String productIMG, String productDesc, int discount, @Min(value = 1, message = "please chose a status") int status, @NotNull int qty, @NotNull double price, Date createAt, Date updateAt, @NotNull Category category, @NotNull Brand brand) {
        this.productID = productID;
        this.productName = productName;
        this.productCode = productCode;
        this.productIMG = productIMG;
        this.productDesc = productDesc;
        this.discount = discount;
        this.status = status;
        this.qty = qty;
        this.price = price;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.category = category;
        this.brand = brand;
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


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductIMG() {
        return productIMG;
    }

    public void setProductIMG(String productIMG) {
        this.productIMG = productIMG;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
