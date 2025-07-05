package com.xadmin.foodmanagement.web;

import com.xadmin.foodmanagement.bean.Menu;

import com.xadmin.foodmanagement.bean.Order;
import com.xadmin.foodmanagement.bean.User;
import com.xadmin.foodmanagement.dao.OrderDao;
import com.xadmin.foodmanagement.dao.OrderItemDao;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import com.xadmin.foodmanagement.bean.User;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDao orderDao;

    public void init() {
        orderDao = new OrderDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "LIST";
        }

        try {
        	switch (action.toUpperCase()) {
            case "NEW":
                showNewForm(request, response);
                break;
            case "INSERT":
                insertOrder(request, response);
                break;
            case "EDIT":
                showEditForm(request, response);
                break;
            case "UPDATE":
                updateOrder(request, response);
                break;
            case "DELETE":
                deleteOrder(request, response);
                return; // ✅ REQUIRED!
            case "VIEW":
                viewOrderDetails(request, response);
                break;
            case "PLACE_ORDER":
                placeOrder(request, response);
                break;
            default:
                listOrders(request, response);
                break;
        }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // =================== Admin CRUD ====================

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("order-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order existingOrder = orderDao.selectOrder(id);
        request.setAttribute("order", existingOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("order-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int userId = Integer.parseInt(request.getParameter("userId"));
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));

        Order order = new Order(userId, totalAmount, orderDate);
        orderDao.insertOrder(order);
        response.sendRedirect("OrderServlet");
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));

        Order updatedOrder = new Order(id, userId, totalAmount, orderDate);
        orderDao.updateOrder(updatedOrder);
        response.sendRedirect("OrderServlet");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        // 🔍 Fetch the order first
        Order order = orderDao.selectOrder(id);  // You must have this method

        // 🔐 Prevent deletion if already Delivered or Completed
        if (order.getStatus().equalsIgnoreCase("Delivered") || order.getStatus().equalsIgnoreCase("Completed")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "⚠️ Completed orders cannot be deleted.");
            return;
        }

        // ✅ Proceed with deletion if not completed
        orderDao.deleteOrder(id);
        response.sendRedirect("OrderServlet");
    }


    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        List<Order> orderList;

        if (user != null && "user".equalsIgnoreCase(user.getRole())) {
            // ✅ Show only their orders
            orderList = orderDao.selectOrdersByUserId(user.getId());
        } else {
            // ✅ Admin can view all
            orderList = orderDao.selectAllOrders();
        }

        request.setAttribute("orderList", orderList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("order-list.jsp");
        dispatcher.forward(request, response);
    }

    private void viewOrderDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderDao.selectOrder(id);

        // ✅ Check status before forwarding
        if (order.getStatus().equals("Delivered") || order.getStatus().equals("Completed")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Completed orders cannot be edited.");
            return; // ✅ THIS IS REQUIRED to prevent the 500 error
        }

        request.setAttribute("order", order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("order-view.jsp");
        dispatcher.forward(request, response);
    }


    // =================== User Cart Checkout ====================

    private void placeOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("menu-list.jsp?error=cart_empty");
            return;
        }

        // ✅ Get logged-in user from session
        com.xadmin.foodmanagement.bean.User user = (com.xadmin.foodmanagement.bean.User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getId(); // ✅ real user ID

        double total = 0;
        for (Menu item : cart) {
            int qty = 1; // Default quantity
            total += item.getPrice() * qty;
        }

        Date today = new Date(System.currentTimeMillis());

        // 🧾 Save order and get generated ID
        Order order = new Order(userId, total, today);
        int orderId = orderDao.insertOrderAndReturnId(order);

        // 💾 Save order items
        OrderItemDao itemDao = new OrderItemDao();
        for (Menu item : cart) {
            int qty = 1; // Default
            itemDao.insertOrderItem(orderId, item.getId(), qty);
        }

        // ✅ Clear cart
        session.removeAttribute("cart");

        // ✅ Set orderId and show confirmation
        request.setAttribute("orderId", String.valueOf(orderId)); // <-- Cast to String to avoid JSP ClassCastException
        RequestDispatcher dispatcher = request.getRequestDispatcher("order-success.jsp");
        dispatcher.forward(request, response);
    }

}
