<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.xadmin.foodmanagement.bean.Menu" %>

<%
    String formAction = request.getAttribute("menu") != null ? "UPDATE" : "INSERT";
    Menu menu = (Menu) request.getAttribute("menu");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= formAction.equals("UPDATE") ? "Edit Food" : "Add Food" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #fff1f2, #f3e5f5);
            font-family: 'Segoe UI', sans-serif;
        }
        .container-box {
            max-width: 500px;
            background-color: #fff;
            padding: 40px 30px;
            border-radius: 20px;
            margin: 60px auto;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
        }
        .form-label {
            font-weight: 500;
        }
        .form-control {
            border-radius: 10px;
            height: 45px;
        }
        .btn-submit {
            width: 130px;
            border-radius: 25px;
        }
        .navbar-brand {
            font-weight: bold;
            font-size: 24px;
        }
        .btn-back {
            border-radius: 25px;
            padding: 8px 20px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-light bg-light px-5 py-3">
    <a class="navbar-brand" href="Home.jsp">🍔 FoodApp</a>
    <a href="MenuServlet" class="btn btn-dark btn-back">Back</a>
</nav>

<!-- Form Card -->
<div class="container-box">
    <h3 class="text-center mb-4"><%= formAction.equals("UPDATE") ? "Edit" : "Add" %> Food</h3>
    <form action="MenuServlet?action=<%=formAction%>" method="post">
        <% if (formAction.equals("UPDATE")) { %>
            <input type="hidden" name="id" value="<%=menu.getId()%>">
        <% } %>

        <div class="mb-3">
            <label class="form-label">Food Name</label>
            <input type="text" name="name" class="form-control" placeholder="Enter Food Name" required
                   value="<%= menu != null ? menu.getName() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label">Food Image URL</label>
            <input type="text" name="imageUrl" class="form-control" placeholder="Enter Food Image URL" required
                   value="<%= menu != null ? menu.getImageUrl() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label">Food Price</label>
            <input type="number" name="price" class="form-control" placeholder="Enter Food Price" required
                   value="<%= menu != null ? menu.getPrice() : "" %>">
        </div>

        <div class="mb-4">
            <label class="form-label">Food Rating</label>
            <input type="number" name="rating" class="form-control" placeholder="Enter Rating" required
       value="<%= menu != null ? menu.getRating() : "" %>">

        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-dark btn-submit">
                <%= formAction.equals("UPDATE") ? "Edit" : "Add" %>
            </button>
        </div>
    </form>
</div>

</body>
</html>
