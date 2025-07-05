package com.xadmin.foodmanagement.dao;

import java.sql.*;
import com.xadmin.foodmanagement.bean.Rating;
import java.util.*;

public class RatingDao {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/FOOD__DB?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "admin@321";

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveRating(Rating rating) {
        String sql = "INSERT INTO ratings (user_id, menu_id, rating) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE rating = VALUES(rating)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rating.getUserId());
            ps.setInt(2, rating.getMenuId());
            ps.setInt(3, rating.getRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getAverageRatingForMenu(int menuId) {
        String sql = "SELECT AVG(rating) FROM ratings WHERE menu_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
