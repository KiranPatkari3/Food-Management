<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login | Food Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            height: 100vh;
            background: linear-gradient(to right, #f8f9fa, #e0f7fa);
            font-family: 'Segoe UI', sans-serif;
        }
        .left-section {
            background: url('images/food-banner.jpg') no-repeat center center;
            background-size: cover;
            border-radius: 0.5rem;
        }
        .overlay {
            background-color: rgba(0, 0, 0, 0.6);
            height: 100%;
            color: white;
            padding: 2rem;
        }
        .login-form {
            background: white;
            border-radius: 1rem;
            padding: 2rem;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        .btn-primary {
            background-color: #0072ff;
            border: none;
        }
    </style>
</head>
<body>
<div class="container-fluid h-100">
    <div class="row h-100 align-items-center justify-content-center">
        <div class="col-lg-6 d-none d-lg-block left-section">
            <div class="overlay d-flex flex-column justify-content-center align-items-start h-100">
                <h1 class="display-4">Welcome to Food Management System</h1>
                <p class="lead mt-3">Manage orders, explore menus, and enjoy seamless food service.</p>
                <p class="mt-4">New here? <a href="register.jsp" class="text-warning fw-bold">Register Now</a></p>
            </div>
        </div>

        <div class="col-lg-4 col-md-8">
            <div class="login-form mt-3 mt-md-0">
                <h3 class="text-center mb-4">Login to Your Account</h3>

                <% String msg = request.getParameter("msg");
                   if ("registered".equals(msg)) { %>
                   <div class="alert alert-success text-center">🎉 Registration successful! Please login.</div>
                <% } %>

                <% if (request.getAttribute("errorMessage") != null) { %>
                    <div class="alert alert-danger text-center">
                        <%= request.getAttribute("errorMessage") %>
                    </div>
                <% } %>

                <form action="login" method="post" autocomplete="off">

                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" name="email" class="form-control" id="email"
       autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                       <input type="password" name="password" class="form-control" id="password"
       autocomplete="new-password" readonly onfocus="this.removeAttribute('readonly');" required>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </form>

                <div class="text-center mt-3">
                    <p>Don't have an account? <a href="register.jsp">Register here</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
