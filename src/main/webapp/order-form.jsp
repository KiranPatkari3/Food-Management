<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.xadmin.foodmanagement.bean.Order" %>

<%
    Order order = (Order) request.getAttribute("order");
    boolean isEdit = (order != null);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Edit Order" : "New Order" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2><%= isEdit ? "Edit Order" : "Add New Order" %></h2>

    <form action="OrderServlet?action=<%= isEdit ? "UPDATE" : "INSERT" %>" method="post">
        <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= order.getId() %>">
        <% } %>

        <div class="mb-3">
            <label for="userId" class="form-label">User ID:</label>
            <input type="number" class="form-control" name="userId" id="userId" required value="<%= isEdit ? order.getUserId() : "" %>">
        </div>

        <div class="mb-3">
            <label for="totalAmount" class="form-label">Total Amount:</label>
            <input type="number" step="0.01" class="form-control" name="totalAmount" id="totalAmount" required value="<%= isEdit ? order.getTotalAmount() : "" %>">
        </div>

        <div class="mb-3">
            <label for="orderDate" class="form-label">Order Date:</label>
            <input type="date" class="form-control" name="orderDate" id="orderDate" required value="<%= isEdit ? order.getOrderDate() : "" %>">
        </div>

        <button type="submit" class="btn btn-success"><%= isEdit ? "Update Order" : "Create Order" %></button>
        <a href="OrderServlet" class="btn btn-secondary">Cancel</a>
    </form>
</div>

</body>
</html>
