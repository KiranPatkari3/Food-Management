package com.xadmin.foodmanagement.bean;

public class Rating {
    private int id;
    private int userId;
    private int menuId;
    private int rating;

    public Rating() {}

    public Rating(int userId, int menuId, int rating) {
        this.userId = userId;
        this.menuId = menuId;
        this.rating = rating;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}
