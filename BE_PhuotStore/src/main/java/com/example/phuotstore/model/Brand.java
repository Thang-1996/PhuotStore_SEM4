package com.example.phuotstore.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandID;

    @NotNull
    private String brandName;

    @NotNull
    private String brandCode;

    @NotNull
    private String brandDesc;

    @Min(value = 1,message = "Please chose a status")
    private int status;

    public Brand() {
    }

    public Brand(int brandID, @NotNull String brandName, @NotNull String brandCode, @NotNull String brandDesc, @Min(value = 1, message = "Please chose a status") int status) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
