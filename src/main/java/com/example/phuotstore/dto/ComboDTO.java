package com.example.phuotstore.dto;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class ComboDTO {

    @NotNull
    private String comboName;

    @NotNull
    private String comboCode;

    @NotNull
    private String comboDesc;

    private int discount;

    @NotNull(message = "Quantity must not be null")
    private int quantity;

    private String status;

    private Set<Integer> product;

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

    public Set<Integer> getProduct() {
        return product;
    }

    public void setProduct(Set<Integer> product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
