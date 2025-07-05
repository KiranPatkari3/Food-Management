package com.xadmin.foodmanagement.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.xadmin.foodmanagement.bean.Menu;
import com.xadmin.foodmanagement.bean.Order;
import com.xadmin.foodmanagement.bean.OrderItem;
import com.xadmin.foodmanagement.dao.OrderDao;
import com.xadmin.foodmanagement.dao.OrderItemDao;

/**
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        Date orderDate = new Date(System.currentTimeMillis());

        Order order = new Order(userId, totalAmount, orderDate);
        int orderId = orderDao.insertOrderReturnId(order); // You'll need to implement this method
        

        HttpSession session = request.getSession();
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");
        
        System.out.println("🛒 Cart: " + cart);
        System.out.println("🛒 Cart size: " + (cart != null ? cart.size() : "null"));
        System.out.println("📦 Order ID: " + orderId);


        for (Menu item : cart) {
            OrderItem orderItem = new OrderItem(orderId, item.getId(), 1); // 1 quantity default
            orderItemDao.insertOrderItem(orderItem);
        }

        session.removeAttribute("cart");
        response.sendRedirect("OrderServlet?action=LIST"); // Show order summary
    }
}
