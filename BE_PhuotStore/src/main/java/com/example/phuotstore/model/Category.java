package com.example.phuotstore.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;

    @NotNull
    private String categoryName;

    @NotNull
    private String categoryCode;

    @NotNull
    private String categoryDesc;

    @NotNull
    private String status;

    public Category() {
    }

    public Category(int categoryID, @NotNull String categoryName, @NotNull String categoryCode, @NotNull String categoryDesc, String status) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.categoryDesc = categoryDesc;
        this.status = status;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
