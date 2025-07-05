<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #fdfbfb, #ebedee);
            font-family: 'Segoe UI', sans-serif;
            min-height: 100vh;
        }
        .dashboard-card {
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            padding: 2rem;
            background-color: #fff;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="dashboard-card text-center">
        <h2 class="mb-3">👋 Welcome to the User Dashboard</h2>
        <p class="text-muted">Explore the menu, place orders, and view your order history.</p>

        <div class="d-grid gap-2 col-6 mx-auto mt-4">
            <a href="MenuServlet" class="btn btn-primary">🍽 View Menu</a>
            <a href="OrderServlet" class="btn btn-success">📦 View My Orders</a>
            <a href="logout.jsp" class="btn btn-outline-danger mt-3">Logout</a>
        </div>
    </div>
</div>
</body>
</html>
