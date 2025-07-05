package com.xadmin.foodmanagement.dao;

import java.sql.*;
import java.util.*;

import com.xadmin.foodmanagement.bean.Menu;

public class MenuDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/FOOD__DB?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin@321";

    private static final String INSERT_MENU_SQL = "INSERT INTO menu (name, price, rating, image_url) VALUES (?, ?, ?, ?)";
    private static final String SELECT_MENU_BY_ID = "SELECT * FROM menu WHERE id = ?";
    private static final String SELECT_ALL_MENUS = "SELECT * FROM menu";
    private static final String DELETE_MENU_SQL = "DELETE FROM menu WHERE id = ?";
    private static final String UPDATE_MENU_SQL = "UPDATE menu SET name = ?, price = ?, rating = ?, image_url = ? WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertMenu(Menu menu) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MENU_SQL)) {
            preparedStatement.setString(1, menu.getName());
            preparedStatement.setDouble(2, menu.getPrice());
            preparedStatement.setDouble(3, menu.getRating());
            preparedStatement.setString(4, menu.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Menu selectMenu(int id) {
        Menu menu = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int rating = rs.getInt("rating");
                String imageUrl = rs.getString("image_url");
                menu = new Menu(id, name, price, rating, imageUrl);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return menu;
    }

    public List<Menu> selectAllMenus() {
        List<Menu> menus = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MENUS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int rating = rs.getInt("rating");
                String imageUrl = rs.getString("image_url");
                menus.add(new Menu(id, name, price, rating, imageUrl));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return menus;
    }

    public boolean deleteMenu(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MENU_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowDeleted;
    }

    public boolean updateMenu(Menu menu) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MENU_SQL)) {
            statement.setString(1, menu.getName());
            statement.setDouble(2, menu.getPrice());
            statement.setDouble(3, menu.getRating());
            statement.setString(4, menu.getImageUrl());
            statement.setInt(5, menu.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    public Menu getMenuById(int id) {
        Menu menu = null;
        String sql = "SELECT * FROM menu WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int rating = rs.getInt("rating");
                String imageUrl = rs.getString("image_url");

                menu = new Menu();
                menu.setId(id);
                menu.setName(name);
                menu.setDescription(description);
                menu.setPrice(price);
                menu.setRating(rating);
                menu.setImageUrl(imageUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menu;
    }

}
