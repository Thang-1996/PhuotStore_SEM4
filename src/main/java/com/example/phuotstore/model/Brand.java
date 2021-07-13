package com.example.phuotstore.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandID;

    @NotNull(message = "Brand Name must not be null")
    private String brandName;

    @NotNull(message = "Brand Code must not be null")
    private String brandCode;

    @NotNull(message = "Brand Description must not be null")
    private String brandDesc;

    @NotNull(message = "Status must not be null")
    private String status;

    public Brand() {
    }

    public Brand(int brandID, String brandName, String brandCode, String brandDesc, String status) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandCode = brandCode;
        this.brandDesc = brandDesc;
        this.status = status;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
