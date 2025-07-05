<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xadmin.foodmanagement.bean.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Users | Food Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<div class="container mt-5">
    <h2 class="mb-4 text-center">Manage Users</h2>

    <%
        List<User> userList = (List<User>) request.getAttribute("userList");
    out.println("DEBUG: Users found in JSP: " + (userList != null ? userList.size() : "null"));
    %>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% if (userList != null && !userList.isEmpty()) {
     for (User user : userList) { 
         if (!"admin".equalsIgnoreCase(user.getRole())) { // ✅ Skip admin
%>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getUsername() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getRole() %></td>
        <td>
            <a href="UserServlet?action=DELETE&id=<%= user.getId() %>"
               class="btn btn-danger btn-sm"
               onclick="return confirm('Are you sure you want to delete this user?');">
                Delete
            </a>
        </td>
    </tr>
<%
         } // end if not admin
     } // end for
   } else { %>
    <tr>
        <td colspan="5" class="text-center text-muted">No users found.</td>
    </tr>
<% } %>

        </tbody>
    </table>
</div>

</body>
</html>
