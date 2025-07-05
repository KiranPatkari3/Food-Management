<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.xadmin.foodmanagement.bean.User" %>
<%
    User currentUser = (User) session.getAttribute("user");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Food Management</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="dashboard.jsp">Dashboard</a>
                </li>

                <%-- Role-specific menu items --%>
                <%
                    if ("admin".equalsIgnoreCase(currentUser.getRole())) {
                %>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-users.jsp">Manage Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-orders.jsp">Manage Orders</a>
                    </li>
                <%
                    } else {
                %>
                    <li class="nav-item">
                        <a class="nav-link" href="my-orders.jsp">My Orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="menu.jsp">Menu</a>
                    </li>
                <%
                    }
                %>
            </ul>

            <span class="navbar-text me-3 text-white">
                Welcome, <%= currentUser.getUsername() %>!
            </span>
            <a href="logout" class="btn btn-outline-light">Logout</a>
        </div>
    </div>
</nav>
