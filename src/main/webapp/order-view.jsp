<%@ page import="com.xadmin.foodmanagement.bean.Order" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    Order order = (Order) request.getAttribute("order");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h2>Order Details</h2>
    <table class="table table-bordered">
        <tr><th>Order ID</th><td><%= order.getId() %></td></tr>
        <tr><th>User ID</th><td><%= order.getUserId() %></td></tr>
        <tr><th>Total Amount</th><td>Rs. <%= order.getTotalAmount() %></td></tr>
        <tr><th>Order Date</th><td><%= order.getOrderDate() %></td></tr>
    </table>
    <a href="OrderServlet" class="btn btn-secondary">Back to Orders</a>
</body>
</html>
