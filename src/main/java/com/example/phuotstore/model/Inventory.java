package com.example.phuotstore.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryID;

    @NotNull(message = "Quantity must not be null")
    private int qtyOnHand;

    @ManyToOne
    @JoinColumn(name = "productID", referencedColumnName = "productID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shopID", referencedColumnName = "shopID")
    private Shop shop;

    public Inventory() {
    }

    public Inventory( int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
