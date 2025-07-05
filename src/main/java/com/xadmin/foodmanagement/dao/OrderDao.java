package com.xadmin.foodmanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.foodmanagement.bean.Order;

public class OrderDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/FOOD__DB?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin@321";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // 1. Insert Order
    public void insertOrder(Order order) {
        String sql = "INSERT INTO orders (user_id, total_amount, order_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalAmount());
            ps.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setString(4, order.getStatus());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2. Insert and return generated order ID
    public int insertOrderReturnId(Order order) {
        int generatedId = 0;
        String sql = "INSERT INTO orders (user_id, total_amount, order_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalAmount());
            ps.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setString(4, order.getStatus());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("❌ Error inserting order: " + e.getMessage());
            e.printStackTrace();
        }

        return generatedId;
    }

    // 3. Select all orders
    public List<Order> selectAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                double totalAmount = rs.getDouble("total_amount");
                Date orderDate = rs.getDate("order_date");
                String status = rs.getString("status");
                orders.add(new Order(id, userId, totalAmount, orderDate, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    // 4. Select order by ID
    public Order selectOrder(int id) {
        Order order = null;
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                double totalAmount = rs.getDouble("total_amount");
                Date orderDate = rs.getDate("order_date");
                String status = rs.getString("status");
                order = new Order(id, userId, totalAmount, orderDate, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    // 5. Update order
    public boolean updateOrder(Order order) {
        boolean rowUpdated = false;
        String sql = "UPDATE orders SET user_id = ?, total_amount = ?, order_date = ?, status = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalAmount());
            ps.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setString(4, order.getStatus());
            ps.setInt(5, order.getId());
            rowUpdated = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // 6. Delete order
    public boolean deleteOrder(int id) {
        boolean rowDeleted = false;
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    // 7. Get orders by user ID
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double totalAmount = rs.getDouble("total_amount");
                Date orderDate = rs.getDate("order_date");
                String status = rs.getString("status");
                orders.add(new Order(id, userId, totalAmount, orderDate, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    // 8. Optional: Update only status
    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int insertOrderAndReturnId(Order order) {
        String sql = "INSERT INTO orders (user_id, total_amount, order_date, status) VALUES (?, ?, ?, ?)";
        int orderId = -1;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalAmount());
            ps.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setString(4, order.getStatus());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }
    public List<Order> selectOrdersByUserId(int userId) throws ClassNotFoundException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getDouble("total_amount"),
                    rs.getDate("order_date")
                );
                order.setStatus(rs.getString("status")); // ✅ Only if your Order class has a status field
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


}
