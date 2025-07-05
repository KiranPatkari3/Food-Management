<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.foodmanagement.bean.Menu" %>
<%@ page import="com.xadmin.foodmanagement.dao.MenuDao" %>
<%@ page import="com.xadmin.foodmanagement.bean.User" %>

<%
    MenuDao menuDao = new MenuDao();
    List<Menu> menuList = menuDao.selectAllMenus();

    User loggedInUser = (User) session.getAttribute("user");
    String role = (loggedInUser != null) ? loggedInUser.getRole() : "user";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background: linear-gradient(to right, #f8cdda, #f1f1f1);
            font-family: 'Segoe UI', sans-serif;
        }
        .navbar {
            background-color: white;
            padding: 1rem 2rem;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .navbar-brand {
            font-weight: bold;
            font-size: 24px;
        }
        .container {
            padding: 40px 60px;
        }
        .card {
            border: none;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            background: white;
            transition: transform 0.3s ease;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .food-image {
            width: 100%;
            height: 220px;
            object-fit: cover;
        }
        .card-body h5 {
            font-weight: bold;
        }
        .btn-edit, .btn-delete, .btn-cart {
            border-radius: 20px;
            padding: 6px 15px;
        }
        .btn-delete {
            border: 1px solid red;
            color: red;
        }
        .star {
            font-size: 20px;
            cursor: pointer;
            color: #ccc;
        }
        .star.active {
            color: #f1c40f;
        }
    </style>
</head>
<body>

<%
    String flashMessage = (String) session.getAttribute("message");
    if (flashMessage != null) {
%>
<div class="alert alert-success alert-dismissible fade show" role="alert" style="margin: 20px;">
    <%= flashMessage %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<%
        session.removeAttribute("message");
    }
%>

<nav class="navbar navbar-expand-lg">
    <a class="navbar-brand" href="#">🍔 FoodApp</a>
    <div class="ms-auto d-flex gap-2">
        <% if ("admin".equalsIgnoreCase(role)) { %>
            <a href="menu-form.jsp" class="btn btn-dark">Add New Food</a>
        <% } %>
        <a href="cart.jsp" class="btn btn-warning">🛒 View Cart</a>
        <a href="Home.jsp" class="btn btn-secondary mb-3">🏠 Home</a>
    </div>
</nav>

<div class="container">
    <div class="row">
        <% for (Menu menu : menuList) { %>
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="<%=menu.getImageUrl()%>" class="food-image" alt="Food Image">
                <div class="card-body">
                    <h5 class="card-title"><%=menu.getName()%></h5>
                    <p class="card-text">Rs. <%=menu.getPrice()%> /-</p>

                    <div class="d-flex justify-content-between flex-wrap gap-2">
                        <% if ("admin".equalsIgnoreCase(role)) { %>
                            <a href="MenuServlet?action=EDIT&id=<%=menu.getId()%>" class="btn btn-dark btn-edit">
                                <i class="fa-solid fa-pen-to-square"></i> Edit
                            </a>
                            <a href="MenuServlet?action=DELETE&id=<%=menu.getId()%>" class="btn btn-delete btn-outline-danger">
                                <i class="fa-solid fa-trash"></i> Delete
                            </a>
                        <% } else { %>
                            <form action="AddToCartServlet" method="post">
                                <input type="hidden" name="menuId" value="<%=menu.getId()%>">
                                <input type="hidden" name="name" value="<%=menu.getName()%>">
                                <input type="hidden" name="price" value="<%=menu.getPrice()%>">
                                <input type="hidden" name="image" value="<%=menu.getImageUrl()%>">
                                <button type="submit" class="btn btn-success btn-cart">
                                    <i class="fa-solid fa-cart-plus"></i> Add to Cart
                                </button>
                            </form>
                        <% } %>
                    </div>

                    <% if ("user".equalsIgnoreCase(role)) { %>
                    <div class="star-rating-form mt-3">
                        <input type="hidden" class="menu-id" value="<%=menu.getId()%>">
                        <input type="hidden" class="selected-stars" value="0">
                        <div class="star-rating">
                            <% for (int i = 1; i <= 5; i++) { %>
                                <i class="fa fa-star star" data-value="<%= i %>"></i>
                            <% } %>
                        </div>
                        <button type="button" class="btn btn-outline-primary btn-sm mt-2 submit-rating-btn">Submit Rating</button>
                    </div>
                    <% } %>

                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll('.star-rating-form').forEach(form => {
        const stars = form.querySelectorAll('.star');
        const hiddenInput = form.querySelector('.selected-stars');
        const menuIdInput = form.querySelector('.menu-id');
        const submitBtn = form.querySelector('.submit-rating-btn');

        stars.forEach(star => {
            star.addEventListener('click', () => {
                const value = parseInt(star.getAttribute('data-value'));
                hiddenInput.value = value;

                stars.forEach(s => {
                    const sVal = parseInt(s.getAttribute('data-value'));
                    s.classList.toggle('active', sVal <= value);
                });
            });
        });

        submitBtn.addEventListener('click', () => {
            const starsValue = hiddenInput?.value?.trim();
            const menuId = menuIdInput?.value?.trim();

            if (!starsValue || starsValue === "0" || !menuId) {
                alert("⚠️ Please select a rating before submitting.");
                return;
            }

            const formData = new URLSearchParams();
            formData.append('menuId', menuId);
            formData.append('stars', starsValue);

            fetch('rate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: formData.toString()
            })
            .then(res => {
                if (res.ok) {
                    alert("✅ Rating submitted!");
                } else {
                    res.text().then(text => alert("❌ Error: " + text));
                }
            })
            .catch(err => {
                console.error('❌ Network error:', err);
                alert("🚫 Network error.");
            });
        });
    });
});
</script>

</body>
</html>
