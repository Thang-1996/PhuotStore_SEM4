package com.example.phuotstore.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderRentID;

    @NotNull(message = "Order Rent Name must not be null")
    private String orderRentName;

    private String note;

    @CreationTimestamp
    private Date createAt;

    @CreationTimestamp
    private Date updateAt;

    @CreationTimestamp
    private Date bookingDate;

    @CreationTimestamp
    private Date rentalStart;

    @CreationTimestamp
    private Date rentalEnd;

    @NotNull(message = "Status must not be null")
    private String status;

    private int totalQuantity;

    private double totalPrice;
    private double deposits;

    private double totalRental;

    private String paymentType;
    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private String phone;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderRentProduct",
        joinColumns = @JoinColumn(name = "orderRentID"),
        inverseJoinColumns = @JoinColumn(name = "productID"))
    private Set<Product> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderRentCombo",
        joinColumns = @JoinColumn(name = "orderRentID"),
        inverseJoinColumns = @JoinColumn(name = "comboID"))
    private Set<Combo> combos = new HashSet<>();

    public OrderRent() {
    }

    public OrderRent(@NotNull String orderRentName, String note, String status,int totalQuantity, double totalPrice, double totalRental,double deposits, Date bookingDate, Date rentalStart, Date rentalEnd,  String firstName, String lastName, String email, String shippingAddress, String phone, String paymentType) {
        this.orderRentName = orderRentName;
        this.note = note;
        this.totalRental = totalRental;
        this.bookingDate = bookingDate;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
        this.status = status;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.deposits = deposits;
        this.firstName= firstName;
        this.lastName= lastName;
        this.email= email;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        this.paymentType = paymentType;
    }

    public int getOrderRentID() {
        return orderRentID;
    }

    public void setOrderRentID(int orderRentID) {
        this.orderRentID = orderRentID;
    }

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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
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

    public double getTotalRental() {
        return totalRental;
    }

    public void setTotalRental(double totalRental) {
        this.totalRental = totalRental;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Combo> getCombos() {
        return combos;
    }

    public void setCombos(Set<Combo> combos) {
        this.combos = combos;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getDeposits() {
        return deposits;
    }

    public void setDeposits(double deposits) {
        this.deposits = deposits;
    }
}
