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
	
	private static final String INSERT_FOODS_SQL = "INSERT INTO foods" + "  (name, price, image, rating) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_FOODS_BY_ID = "select id,name,price,image, rating from foods where id =?";
	private static final String SELECT_ALL_FOODS = "select * from foods";
	private static final String DELETE_FOODS_SQL = "delete from foods where id = ?;";
	private static final String UPDATE_FOODS_SQL = "update foods set name = ?,price= ?, image =?, rating=? where id = ?;";
	
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			System.out.println("Database connection failure: " + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
	public void insertFood(Food food) throws SQLException {
		System.out.println(INSERT_FOODS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOODS_SQL)) {
			preparedStatement.setString(1, food.getName());
			preparedStatement.setString(2, food.getImage());
			preparedStatement.setString(3, food.getPrice());
			preparedStatement.setString(4, food.getRating());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
//			printSQLException(e);
		}
	}
	
	
	
	public Food selectFood(int id) {
		Food food = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FOODS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String price = rs.getString("price");
				String image = rs.getString("image");
				String rating = rs.getString("rating");
				food = new Food(id, name, price, image, rating);
			}
		} catch (SQLException e) {
//			printSQLException(e);
		}
		return food;
	}
	
	
	
	
	
	
	public List<Food> selectAllFoods() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Food> foods = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FOODS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String price = rs.getString("price");
				String image = rs.getString("image");
				String rating = rs.getString("rating");
				foods.add(new Food(id, name, price, image, rating));
			}
		} catch (SQLException e) {
//			printSQLException(e);
		}
		return foods;
	}
	
	
	
	
	
	public boolean deleteFood(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FOODS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	
	
	public boolean updateFood(Food food) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FOODS_SQL);) {
			statement.setString(1, food.getName());
			statement.setString(2, food.getPrice());
			statement.setString(3, food.getImage());
			statement.setString(4, food.getRating());
			statement.setInt(5, food.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

}
