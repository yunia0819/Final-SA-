package com.example.final_project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int rentalId; // 對應 rental_id

    @Column(name = "user_id", nullable = false)
    private int userId; // 對應 user_id

    @Column(name = "furniture_name", nullable = false, length = 255)
    private String furnitureName; // 對應 furniture_name

    @Column(name = "quantity", nullable = false)
    private int quantity; // 對應 quantity

    @Column(name = "date", nullable = false)
    private String date; // 對應 date

    @Column(name = "total_price", nullable = false)
    private Double totalPrice; // 對應 total_price

    @Column(name = "payed",nullable = false)
    private boolean payed = true; // 對應 payed

    // Constructors
    public Rental() {}

    public Rental(int userId, String furnitureName, int quantity, String date, Double totalPrice, boolean payed) {
        this.userId = userId;
        this.furnitureName = furnitureName;
        this.quantity = quantity;
        this.date = date;
        this.totalPrice = totalPrice;
        this.payed = payed;
    }

    // Getters and Setters
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
