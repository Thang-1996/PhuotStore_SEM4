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

    @NotNull(message = "Product Name must not be null")
    private String productName;

    @NotNull(message = "Product Code must not be null")
    private String productCode;

    @NotNull(message = "Product Description must not be null")
    private String productDesc;

    private int discount;

    @NotNull(message = "Status must not be null")
    private String status;

    @NotNull(message = "Quantity must not be null")
    private int qty;

    @NotNull(message = "Price must not be null")
    private double price;

    @CreationTimestamp
    private Date createAt;
    @CreationTimestamp
    private Date updateAt;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brandID", referencedColumnName = "brandID")
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();


    public Product() {
    }

    public Product(int productID, String productName, String productCode, String productDesc, int discount, String status, int qty, double price, Date createAt, Date updateAt, int rating, Category category, Brand brand, Set<Image> images, Set<Comment> comments) {
        this.productID = productID;
        this.productName = productName;
        this.productCode = productCode;
        this.productDesc = productDesc;
        this.discount = discount;
        this.status = status;
        this.qty = qty;
        this.price = price;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.rating = rating;
        this.category = category;
        this.brand = brand;
        this.images = images;
        this.comments = comments;
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
