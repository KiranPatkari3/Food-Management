<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New User | Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f4f6f9;
            font-family: 'Segoe UI', sans-serif;
        }
        .form-container {
            margin-top: 50px;
            padding: 30px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<div class="container col-md-6 offset-md-3 form-container">
    <h2>Add New User</h2>
    <form action="UserServlet?action=INSERT" method="post" autocomplete="off">
        <div class="mb-3">
            <label for="username" class="form-label">Full Name</label>
            <input type="text" name="username" class="form-control" id="username" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email Address</label>
            <input type="email" name="email" class="form-control" id="email" required>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" name="password" class="form-control" id="password" required>
        </div>

        <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <select name="role" id="role" class="form-select" required>
                <option value="user" selected>User</option>
                <option value="admin">Admin</option>
            </select>
        </div>

        <div class="d-grid">
            <button type="submit" class="btn btn-success">Save User</button>
        </div>

        <div class="text-center mt-3">
            <a href="UserServlet" class="btn btn-link">← Back to User List</a>
        </div>
    </form>
</div>
</body>
</html>
