package com.example.phuotstore.dto;

import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

public class OrderRentDTO {

    @NotNull
    private String orderRentName;

    private String note;

    private String status;

    private int totalQuantity;

    private double totalPrice;

    private double rental;

    @CreationTimestamp
    private Date bookingDate;

    @CreationTimestamp
    private Date rentalStart;

    @CreationTimestamp
    private Date rentalEnd;

    private Set<Integer> user;

    private Set<Integer> product;

    private Set<Integer> combo;

    public String getOrderRentName() {
        return orderRentName;
    }

    public void setOrderRentName(String orderRentName) {
        this.orderRentName = orderRentName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getRental() {
        return rental;
    }

    public void setRental(double rental) {
        this.rental = rental;
    }

    public Set<Integer> getUser() {
        return user;
    }

    public void setUser(Set<Integer> user) {
        this.user = user;
    }

    public Set<Integer> getProduct() {
        return product;
    }

    public void setProduct(Set<Integer> product) {
        this.product = product;
    }

    public Set<Integer> getCombo() {
        return combo;
    }

    public void setCombo(Set<Integer> combo) {
        this.combo = combo;
    }

    public Date getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

    public Date getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(Date rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
