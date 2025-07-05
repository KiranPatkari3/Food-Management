package com.xadmin.foodmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.foodmanagement.bean.Food;

public class FoodDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/FOOD__DB?user=root&password=admin@321&allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin@321";

    private static final String INSERT_FOODS_SQL = "INSERT INTO foods (name, price, image, rating) VALUES (?, ?, ?, ?);";
    private static final String SELECT_FOODS_BY_ID = "SELECT id, name, price, image, rating FROM foods WHERE id = ?";
    private static final String SELECT_ALL_FOODS = "SELECT * FROM foods";
    private static final String DELETE_FOODS_SQL = "DELETE FROM foods WHERE id = ?";
    private static final String UPDATE_FOODS_SQL = "UPDATE foods SET name = ?, price = ?, image = ?, rating = ? WHERE id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.out.println("❌ Database connection failure: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public void insertFood(Food food) throws SQLException {
        System.out.println("📥 Executing INSERT: " + INSERT_FOODS_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOODS_SQL)) {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getPrice());
            preparedStatement.setString(3, food.getImage());
            preparedStatement.setString(4, food.getRating());
            System.out.println("👉 SQL = " + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error inserting food");
            e.printStackTrace();
        }
    }

    public Food selectFood(int id) {
        Food food = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FOODS_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println("🔍 Executing SELECT by ID: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String price = rs.getString("price");
                String image = rs.getString("image");
                String rating = rs.getString("rating");
                food = new Food(id, name, price, image, rating);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error selecting food by ID");
            e.printStackTrace();
        }
        return food;
    }

    public List<Food> selectAllFoods() {
        List<Food> foods = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FOODS)) {
            System.out.println("📄 Executing SELECT ALL: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String price = rs.getString("price");
                String image = rs.getString("image");
                String rating = rs.getString("rating");
                foods.add(new Food(id, name, price, image, rating));
            }

            System.out.println("✅ Fetched " + foods.size() + " food items.");
        } catch (SQLException e) {
            System.out.println("❌ Error fetching all foods");
            e.printStackTrace();
        }
        return foods;
    }

    public boolean deleteFood(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FOODS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting food");
            e.printStackTrace();
            rowDeleted = false;
        }
        return rowDeleted;
    }

    public boolean updateFood(Food food) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_FOODS_SQL)) {
            statement.setString(1, food.getName());
            statement.setString(2, food.getPrice());
            statement.setString(3, food.getImage());
            statement.setString(4, food.getRating());
            statement.setInt(5, food.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating food");
            e.printStackTrace();
            rowUpdated = false;
        }
        return rowUpdated;
    }
}
