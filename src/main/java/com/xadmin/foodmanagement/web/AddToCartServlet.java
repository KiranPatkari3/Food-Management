package com.xadmin.foodmanagement.web;

import com.xadmin.foodmanagement.bean.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");

        HttpSession session = request.getSession();
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        // Check if item already in cart, just increase quantity
        boolean itemExists = false;
        for (Menu item : cart) {
            if (item.getId() == menuId) {
                item.setQuantity(item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            Menu menuItem = new Menu();
            menuItem.setId(menuId);
            menuItem.setName(name);
            menuItem.setPrice(price);
            menuItem.setImageUrl(image);
            menuItem.setQuantity(1);
            cart.add(menuItem);
        }

        session.setAttribute("message", "✅ " + name + " added to cart!");
        response.sendRedirect("menu-list.jsp");
    }

    // ✅ Optional: handle GET with an error message
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "POST method expected");
    }
}
