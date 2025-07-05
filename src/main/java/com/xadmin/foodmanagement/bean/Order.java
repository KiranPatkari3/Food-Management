package com.xadmin.foodmanagement.bean;

import java.sql.Date;

public class Order {
    private int id;
    private int userId;
    private double totalAmount;
    private Date orderDate;
    private String status;

    // ✅ Full Constructor
    public Order(int id, int userId, double totalAmount, Date orderDate, String status) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
    }

    // ✅ Constructor used in INSERT (without id)
    public Order(int userId, double totalAmount, Date orderDate) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = "Pending"; // default status
    }

    // ✅ Constructor used in update
    public Order(int id, int userId, double totalAmount, Date orderDate) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    // ✅ Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
