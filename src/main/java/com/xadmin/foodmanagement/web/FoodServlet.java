package com.xadmin.foodmanagement.web;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.xadmin.foodmanagement.bean.Food;
import com.xadmin.foodmanagement.dao.FoodDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;





/**
 * Servlet implementation class FoodServlet
 */
@WebServlet("/")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FoodDao foodsDao;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public FoodServlet() {
        this.foodsDao = new FoodDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) 
			{
			case "/add-food":
				showNewForm(request, response);
				break;
				
			case "/insert":
				insertFood(request, response);
				break;
			
			case "/edit":
				showEditForm(request, response);
				break;
			
			case "/update":
				updateFood(request, response);
				break;
			
			case "/delete":
				deleteFood(request, response);
				break;
				
			default:
				listFood(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}	
		
		
	}
	
	
	
	private void listFood(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Food> listFood = foodsDao.selectAllFoods();
		request.setAttribute("listFood", listFood);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Food.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("add-food.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void insertFood(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String image = request.getParameter("image");
		String rating = request.getParameter("rating");
        Food newFood = new Food(name, image, price, rating);
        foodsDao.insertFood(newFood);
		response.sendRedirect("food");
	}
	
	
	
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Food existingPlace = foodsDao.selectFood(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit-food.jsp");
		request.setAttribute("food", existingPlace);
		dispatcher.forward(request, response);

	}
	
	
	
	private void updateFood(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String image = request.getParameter("image");
		String rating = request.getParameter("rating");
		
		System.out.println(id);
		System.out.println(name);
		System.out.println(price);
		System.out.println(image);
		System.out.println(rating);

		Food book = new Food(id, name, price, image, rating);
		foodsDao.updateFood(book);
		response.sendRedirect("food");
	}
	
	
	
	
	private void deleteFood(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		foodsDao.deleteFood(id);
		response.sendRedirect("food");

	}
	


	
	

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
