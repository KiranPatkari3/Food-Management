<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.foodmanagement.bean.OrderItem" %>

<%
    List<OrderItem> orderItemList = (List<OrderItem>) request.getAttribute("itemList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Item List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>🧾 All Order Items</h2>
        <div>
            <a href="Home.jsp" class="btn btn-secondary">🏠 Home</a>
        </div>
    </div>

    <!-- 🔍 Filter by Order ID -->
    <form action="OrderItemServlet" method="get" class="mb-3 row g-2">
        <div class="col-auto">
            <input type="number" name="orderId" class="form-control" placeholder="Enter Order ID" required>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary">Filter</button>
            <a href="OrderItemServlet" class="btn btn-outline-secondary">Reset</a>
        </div>
    </form>

    <table class="table table-bordered table-striped text-center">
        <thead class="table-dark">
            <tr>
                <th>Item ID</th>
                <th>Order ID</th>
                <th>Food Name</th>
                <th>Quantity</th>
                <th>Price (₹)</th>
            </tr>
        </thead>
        <tbody>
        <% if (orderItemList != null && !orderItemList.isEmpty()) {
            for (OrderItem item : orderItemList) { %>
                <tr>
                    <td><%= item.getId() %></td>
                    <td><%= item.getOrderId() %></td>
                    <td><%= item.getFoodName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.getPrice() %></td>
                </tr>
        <%  }
           } else { %>
            <tr>
                <td colspan="5">No Order Items Found</td>
            </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
