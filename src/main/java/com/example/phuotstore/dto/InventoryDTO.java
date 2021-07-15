package com.example.phuotstore.dto;

import javax.validation.constraints.NotNull;

public class InventoryDTO {

    @NotNull(message = "Quantity must not be null")
    private int qtyOnHand;

    private int productID;

    private int shopID;

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }
}
