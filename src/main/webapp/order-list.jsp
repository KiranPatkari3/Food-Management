<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.foodmanagement.bean.Order" %>

<%
    List<Order> orderList = (List<Order>) request.getAttribute("orderList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="mb-0">📦 Orders</h2>
        <a href="Home.jsp" class="btn btn-secondary">🏠 Home</a>
    </div>

    
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Total Amount</th>
                <th>Order Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% if (orderList != null) {
                for (Order order : orderList) { %>
                    <tr>
                        <td><%= order.getId() %></td>
                        <td><%= order.getUserId() %></td>
                        <td>₹<%= order.getTotalAmount() %></td>
                        <td><%= order.getOrderDate() %></td>
                        <td>
                            <a href="OrderServlet?action=VIEW&id=<%=order.getId()%>" class="btn btn-info">
                                    View</a>

                            <a href="OrderServlet?action=DELETE&id=<%= order.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure to delete this order?')"> Delete</a>
                        </td>
                    </tr>
            <%  }
            } else { %>
                <tr><td colspan="5" class="text-center">No orders found.</td></tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
