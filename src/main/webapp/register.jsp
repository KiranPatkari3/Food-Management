<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register | Food Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            height: 100vh;
            background: linear-gradient(to right, #fffde7, #e1f5fe);
            font-family: 'Segoe UI', sans-serif;
        }
        .left-section {
            background: url('images/food-register.jpg') no-repeat center center;
            background-size: cover;
            border-radius: 0.5rem;
        }
        .overlay {
            background-color: rgba(0, 0, 0, 0.6);
            height: 100%;
            color: white;
            padding: 2rem;
        }
        .register-form {
            background: white;
            border-radius: 1rem;
            padding: 2rem;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        .btn-success {
            background-color: #28a745;
            border: none;
        }
        .btn-success:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="container-fluid h-100">
    <div class="row h-100 align-items-center justify-content-center">
        <!-- Left: Info Section -->
        <div class="col-lg-6 d-none d-lg-block left-section">
            <div class="overlay d-flex flex-column justify-content-center align-items-start h-100">
                <h1 class="display-4">Join the Food Management System</h1>
                <p class="lead mt-3">Create your account to explore menus, manage food, and enjoy personalized experiences.</p>
                <p class="mt-4">Already have an account? <a href="login.jsp" class="text-warning fw-bold">Login Here</a></p>
            </div>
        </div>

        <!-- Right: Register Form -->
        <div class="col-lg-4 col-md-8">
            <div class="register-form">
                <h3 class="text-center mb-4">Create Your Account</h3>

                <!-- Show error message if present -->
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <div class="alert alert-danger text-center">
                        <%= request.getAttribute("errorMessage") %>
                    </div>
                <% } %>

                <form action="register" method="post" autocomplete="off">
                    <div class="mb-3">
                        <label for="username" class="form-label">Full Name</label>
                        <input type="text" name="username" class="form-control" id="username"
                               autocomplete="new-username" value="" readonly onfocus="this.removeAttribute('readonly');" required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" name="email" class="form-control" id="email"
                               autocomplete="new-email" value="" readonly onfocus="this.removeAttribute('readonly');" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" id="password"
                               autocomplete="new-password" value="" readonly onfocus="this.removeAttribute('readonly');" required>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-success">Register</button>
                    </div>
                </form>

                <div class="text-center mt-3">
                    <p>Already registered? <a href="login.jsp">Login here</a></p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
