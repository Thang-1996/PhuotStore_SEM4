package com.example.phuotstore.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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

    private double rental;

    @CreationTimestamp
    private Date createAt;
    @CreationTimestamp
    private Date updateAt;

    private int rating;

    @Column(columnDefinition="TEXT")
    private String images;

    @ManyToOne
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brandID", referencedColumnName = "brandID")
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    public Product() {
    }

    public Product(String productName, String productCode, String productDesc, String images, int discount, String status, int qty, double price, double rental, int rating) {
        this.productName = productName;
        this.productCode = productCode;
        this.images = images;
        this.productDesc = productDesc;
        this.discount = discount;
        this.status = status;
        this.qty = qty;
        this.price = price;
        this.rental = rental;
        this.rating = rating;
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

    public double getRental() {
        return rental;
    }

    public void setRental(double rental) {
        this.rental = rental;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
