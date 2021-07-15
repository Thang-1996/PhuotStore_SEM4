package com.example.phuotstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imgID;

    @NotNull(message = "Image Name must not be null")
    private String imgName;

    @NotNull(message = "Image URL must not be null")
    private String  imgURL;

    @NotNull(message = "Status must not be null")
    private String status;

    @ManyToOne
    @JoinColumn(name = "productID", referencedColumnName = "productID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Product product;

    public Image() {
    }

    public Image(@NotNull String imgName, @NotNull String imgURL, @NotNull String status) {
        this.imgName = imgName;
        this.imgURL = imgURL;
        this.status = status;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
