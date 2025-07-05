<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #fff1eb, #ace0f9);
            font-family: 'Segoe UI', sans-serif;
            min-height: 100vh;
        }
        .admin-card {
            border-radius: 15px;
            padding: 2rem;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            background: white;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="admin-card text-center">
        <h2 class="mb-3">🛠 Admin Dashboard</h2>
        <p class="text-muted">Manage menu items, orders, and users from here.</p>

        <div class="d-grid gap-2 col-6 mx-auto mt-4">
            <a href="MenuServlet" class="btn btn-warning">🍕 Manage Menu</a>
            <a href="OrderServlet" class="btn btn-info">📋 View All Orders</a>
            <a href="UserServlet" class="btn btn-dark">👥 Manage Users</a>
            <a href="logout.jsp" class="btn btn-outline-danger mt-3">Logout</a>
        </div>
    </div>
</div>
</body>
</html>
