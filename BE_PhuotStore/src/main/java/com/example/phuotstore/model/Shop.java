package com.example.phuotstore.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shopID;

    @NotNull
    private String shopName;

    private String phone;

    @NotNull
    private String address;

    @NotNull
    private String status;

    private String country;

    private String region;

    public Shop() {
    }

    public Shop(int shopID, @NotNull String shopName, String phone, @NotNull String address, @NotNull String status, String country, String region) {
        this.shopID = shopID;
        this.shopName = shopName;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.country = country;
        this.region = region;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
