package com.xadmin.foodmanagement.web;

import com.xadmin.foodmanagement.bean.Food;
import com.xadmin.foodmanagement.bean.Menu;
import com.xadmin.foodmanagement.dao.FoodDao;
import com.xadmin.foodmanagement.dao.MenuDao;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuDao menuDao;

    public void init() {
        menuDao = new MenuDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action == null ? "LIST" : action.toUpperCase()) {
                case "NEW":
                    showNewForm(request, response);
                    break;
                case "INSERT":
                    insertMenu(request, response);
                    break;
                case "EDIT":
                    showEditForm(request, response);
                    break;
                case "UPDATE":
                    updateMenu(request, response);
                    break;
                case "DELETE":
                    deleteMenu(request, response);
                    break;
                default:
                    listMenu(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FoodDao foodDao = new FoodDao();
        List<Food> listFood = foodDao.selectAllFoods(); // ✅ get the data
        request.setAttribute("listFood", listFood);     // ✅ pass it to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu-list.jsp");
        dispatcher.forward(request, response);
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Menu existingMenu = menuDao.selectMenu(id);
        request.setAttribute("menu", existingMenu);
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMenu(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String imageUrl = request.getParameter("imageUrl");

        Menu newMenu = new Menu(name, price, rating, imageUrl);
        menuDao.insertMenu(newMenu);
        response.sendRedirect("MenuServlet");
    }

    private void updateMenu(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String imageUrl = request.getParameter("imageUrl");

        Menu updatedMenu = new Menu(id, name, price, rating, imageUrl);
        menuDao.updateMenu(updatedMenu);
        response.sendRedirect("MenuServlet");
    }

    private void deleteMenu(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        menuDao.deleteMenu(id);
        response.sendRedirect("MenuServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
