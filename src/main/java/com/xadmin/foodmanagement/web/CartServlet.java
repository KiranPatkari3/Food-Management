package com.xadmin.foodmanagement.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.foodmanagement.bean.Menu;
import com.xadmin.foodmanagement.dao.MenuDao;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private MenuDao menuDao = new MenuDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        List<Menu> cart = (List<Menu>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        if ("ADD".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Menu item = menuDao.selectMenu(id);
            cart.add(item);
        } else if ("CLEAR".equals(action)) {
            cart.clear();
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}
