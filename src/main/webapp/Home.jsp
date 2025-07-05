<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.xadmin.foodmanagement.bean.User" %>
<%
    HttpSession sessionObj = request.getSession(false);
    User user = null;
    String role = "guest";
    if (sessionObj != null) {
        user = (User) sessionObj.getAttribute("user");
        if (user != null) {
            role = user.getRole();
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to FoodApp 🍽</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #fdfbfb, #ebedee);
            min-height: 100vh;
        }
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .btn {
            font-size: 18px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="Home.jsp">🍽 FoodApp</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <% if (user != null) { %>
                    <li class="nav-item"><span class="nav-link text-light">Welcome, <%= user.getUsername() %> 👋</span></li>
                   
                    <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                <% } else { %>
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center mb-3">Welcome to the Food Management System 🍕</h1>
    <p class="text-center text-muted">Your one-stop platform for managing food and orders.</p>

    <div class="row mt-4">

        <% if ("admin".equalsIgnoreCase(role)) { %>
            <!-- ADMIN OPTIONS -->
            <div class="col-md-4 mb-4">
                <div class="card text-center p-4">
                    <h4 class="mb-3">Manage Menu</h4>
                    <p>View and manage all food items available on the menu.</p>
                    <a href="MenuServlet" class="btn btn-primary w-100"><i class="bi bi-list"></i> Manage Menu</a>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card text-center p-4">
                    <h4 class="mb-3">All Orders</h4>
                    <p>View and manage all customer orders.</p>
                    <a href="OrderServlet" class="btn btn-success w-100"><i class="bi bi-basket"></i> View Orders</a>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card text-center p-4">
                    <h4 class="mb-3">Order Items</h4>
                    <p>See what items were included in each order.</p>
                    <a href="OrderItemServlet" class="btn btn-warning w-100"><i class="bi bi-bag-fill"></i> View Items</a>
                </div>
            </div>

        <% } else if ("user".equalsIgnoreCase(role)) { %>
            <!-- USER OPTIONS -->
            <div class="col-md-4 mb-4">
                <div class="card text-center p-4">
                    <h4 class="mb-3">View Menu</h4>
                    <p>Explore available food items and add to cart.</p>
                    <a href="MenuServlet" class="btn btn-primary w-100"><i class="bi bi-list"></i> View Menu</a>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card text-center p-4">
                    <h4 class="mb-3">My Orders</h4>
                    <p>Track all your past orders and statuses.</p>
                    <a href="OrderServlet" class="btn btn-success w-100"><i class="bi bi-receipt"></i> My Orders</a>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card text-center p-4">
                    <h4 class="mb-3">My Cart</h4>
                    <p>Review items you’ve added to your cart.</p>
                    <a href="cart.jsp" class="btn btn-warning w-100"><i class="bi bi-cart"></i> View Cart</a>
                </div>
            </div>

        <% } else { %>
            <!-- GUEST / NOT LOGGED IN -->
            <div class="col-md-12">
                <div class="alert alert-info text-center">
                    <strong>Please login</strong> to access dashboard features.
                </div>
            </div>
        <% } %>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
