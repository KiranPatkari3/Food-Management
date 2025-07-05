package com.xadmin.foodmanagement.dao;

import com.xadmin.foodmanagement.bean.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/FOOD__DB";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin@321";

    private static final String INSERT_ITEM_SQL =
            "INSERT INTO order_items (order_id, food_id, quantity) VALUES (?, ?, ?)";

    private static final String SELECT_ITEMS_BY_ORDER_ID =
    	    "SELECT oi.id, oi.order_id, oi.food_id, f.name AS food_name, oi.quantity, " +
    	    "(CAST(f.price AS DECIMAL(10,2)) * oi.quantity) AS price " +
    	    "FROM order_items oi " +
    	    "JOIN foods f ON oi.food_id = f.id " +
    	    "WHERE oi.order_id = ?";

    	private static final String SELECT_ALL_ITEMS =
    	    "SELECT oi.id, oi.order_id, oi.food_id, f.name AS food_name, oi.quantity, " +
    	    "(CAST(f.price AS DECIMAL(10,2)) * oi.quantity) AS price " +
    	    "FROM order_items oi " +
    	    "JOIN foods f ON oi.food_id = f.id";


    // ✅ Connection utility
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            System.out.println("❌ DB Connection Failed");
            e.printStackTrace();
        }
        return connection;
    }

    

    // ✅ Insert Order Item using parameters
    public void insertOrderItem(int orderId, int foodId, int quantity) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_ITEM_SQL)) {
            ps.setInt(1, orderId);
            ps.setInt(2, foodId);
            ps.setInt(3, quantity);
            int rows = ps.executeUpdate();
            System.out.println("Inserted order item rows: " + rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Insert OrderItem using Object
    public void insertOrderItem(OrderItem item) {
        insertOrderItem(item.getOrderId(), item.getMenuId(), item.getQuantity());
    }

    // ✅ Get all items for a specific Order ID
    public List<OrderItem> getItemsByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ITEMS_BY_ORDER_ID)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setMenuId(rs.getInt("food_id")); // Still setting it to menuId field in bean
                item.setFoodName(rs.getString("food_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    // ✅ Get all order items (for admin)
    public List<OrderItem> selectAllOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_ITEMS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setMenuId(rs.getInt("food_id")); // Still setting it to menuId field in bean
                item.setFoodName(rs.getString("food_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
