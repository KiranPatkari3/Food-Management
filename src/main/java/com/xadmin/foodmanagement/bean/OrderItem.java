package com.xadmin.foodmanagement.bean;

public class OrderItem {
    private int id;
    private int orderId;
    private int menuId;
    private String foodName;
    private int quantity;
    private double price;

    public OrderItem(int id, int orderId, int menuId, String foodName, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.menuId = menuId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }
    
    public OrderItem(int orderId, int menuId, int quantity) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
    }
    public OrderItem() {
    }

    // Getters and setters
    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public int getMenuId() { return menuId; }
    public String getFoodName() { return foodName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setId(int id) { this.id = id; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
}
