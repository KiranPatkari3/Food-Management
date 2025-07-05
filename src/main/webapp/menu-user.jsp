<!-- menu-user.jsp -->
<%@ page import="java.util.*, com.xadmin.foodmanagement.bean.Menu" %>
<%@ page session="true" %>
<html>
<head><title>Menu</title></head>
<body>
    <h2>Available Food Items</h2>
    <table border="1">
        <tr><th>Name</th><th>Price</th><th>Rating</th><th>Action</th></tr>
        <%
            List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
            for (Menu item : menuList) {
        %>
        <tr>
            <td><%= item.getName() %></td>
            <td><%= item.getPrice() %></td>
            <td><%= item.getRating() %></td>
            <td><a href="CartServlet?action=ADD&id=<%= item.getId() %>">Add to Cart</a></td>
        </tr>
        <% } %>
    </table>
    <a href="cart.jsp">Go to Cart</a>
</body>
</html>
