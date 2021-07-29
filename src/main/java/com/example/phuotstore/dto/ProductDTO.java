package com.example.phuotstore.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class ProductDTO {

    @NotNull(message = "Product Name must not be null")
    private String productName;

    @NotNull(message = "Product Code must not be null")
    private String productCode;

    @NotNull(message = "Product Description must not be null")
    private String productDesc;

    private int discount;

    @NotNull(message = "Quantity must not be null")
    private int qty;

    @NotNull(message = "Price must not be null")
    private double price;
    private int rating;

    @Column(columnDefinition="TEXT")
    private String images;


    @NotNull(message = "Status must not be null")
    private String status;
    private int categoryID;
    private int brandID;
    private Set<Integer> comments;

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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Set<Integer> getComments() {
        return comments;
    }

    public void setComments(Set<Integer> comments) {
        this.comments = comments;
    }
}

