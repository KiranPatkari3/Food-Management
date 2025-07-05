<%@ page import="com.xadmin.foodmanagement.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sessionObj = request.getSession(false);
    User user = (User) sessionObj.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String username = user.getUsername();
    String role = user.getRole();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Food Management</a>
        <div class="d-flex">
            <span class="navbar-text text-light me-3">
                Welcome, <%= username %> (<%= role %>)
            </span>
            <a class="btn btn-outline-light" href="logout">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <% if ("admin".equalsIgnoreCase(role)) { %>
        <h2 class="mb-4">Admin Dashboard</h2>
        <div class="row g-4">
            <div class="col-md-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Manage Foods</h5>
                        <a href="list-foods.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Manage Users</h5>
                        <a href="list-users.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">View Orders</h5>
                        <a href="list-orders.jsp" class="btn btn-primary">Go</a>
                    </div>
                </div>
            </div>
        </div>
    <% } else { %>
        <h2 class="mb-4">User Dashboard</h2>
        <div class="row g-4">
            <div class="col-md-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Browse Menu</h5>
                        <a href="view-menu.jsp" class="btn btn-success">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">My Orders</h5>
                        <a href="my-orders.jsp" class="btn btn-success">Go</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">My Profile</h5>
                        <a href="profile.jsp" class="btn btn-success">Go</a>
                    </div>
                </div>
            </div>
        </div>
    <% } %>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
