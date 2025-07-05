package com.xadmin.foodmanagement.web;

import com.xadmin.foodmanagement.bean.OrderItem;
import com.xadmin.foodmanagement.dao.OrderItemDao;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/OrderItemServlet")
public class OrderItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderItemDao orderItemDao;

    public void init() {
        orderItemDao = new OrderItemDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Optional: You can add action-based routing later
        handleListItems(request, response);
    }

    private void handleListItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String orderIdParam = request.getParameter("orderId");
            List<OrderItem> items;

            if (orderIdParam != null && !orderIdParam.isEmpty()) {
                try {
                    int orderId = Integer.parseInt(orderIdParam);
                    items = orderItemDao.getItemsByOrderId(orderId);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Invalid Order ID format.");
                    items = orderItemDao.selectAllOrderItems();
                }
            } else {
                items = orderItemDao.selectAllOrderItems();
            }

            request.setAttribute("itemList", items);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orderitem-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong while loading order items.");
            request.getRequestDispatcher("orderitem-list.jsp").forward(request, response);
        }
    }
}
