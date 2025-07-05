package com.xadmin.foodmanagement.bean;

public class Menu {
    private int id;
    private String name;
    private String description;
    private double price;
    private int rating;
    private String imageUrl;
    private int quantity; // ✅ for cart item quantity

    // ✅ No-arg constructor
    public Menu() {
    }

    // ✅ All-args constructor (for creating object directly)
    public Menu(int id, String name, double price, int rating, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }
    // ✅ Constructor without ID (for insertion)
    public Menu(String name, double price, int rating, String imageUrl) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }


    // ✅ Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
