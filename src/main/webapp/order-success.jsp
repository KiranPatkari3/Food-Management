<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    // Safely retrieve orderId from request attribute or fallback to parameter
    String orderId = String.valueOf(request.getAttribute("orderId"));
    if (orderId == null || "null".equals(orderId)) {
        orderId = request.getParameter("orderId");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Successful</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f9f9f9;
            font-family: 'Segoe UI', sans-serif;
        }
        .success-container {
            margin-top: 100px;
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .success-icon {
            font-size: 48px;
            color: green;
        }
        a.btn-back {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="success-container">
    <div class="success-icon">✅</div>
    <h2 class="mt-3">Order Placed Successfully!</h2>
    <p class="lead">Your Order ID is: <strong><%= orderId %></strong></p>
    <a href="menu-list.jsp" class="btn btn-primary btn-back">← Back to Menu</a>
</div>

</body>
</html>
