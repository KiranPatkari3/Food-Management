<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.xadmin.foodmanagement.bean.Menu" %>
<%
    HttpSession sessionCart = request.getSession();
    List<Menu> cartItems = (List<Menu>) sessionCart.getAttribute("cart");

    if (cartItems == null) {
        cartItems = new ArrayList<>();
        sessionCart.setAttribute("cart", cartItems);
    }

    // Group by item ID (for duplicate items)
    Map<Integer, Menu> groupedItems = new LinkedHashMap<>();
    for (Menu item : cartItems) {
        if (groupedItems.containsKey(item.getId())) {
            Menu existing = groupedItems.get(item.getId());
            existing.setQuantity(existing.getQuantity() + 1);
        } else {
            item.setQuantity(1); // default quantity
            groupedItems.put(item.getId(), item);
        }
    }

    String message = (String) session.getAttribute("message");
    if (message != null) {
%>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <%= message %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
        session.removeAttribute("message");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function updateTotal() {
            let rows = document.querySelectorAll("tbody tr");
            let total = 0;
            rows.forEach(row => {
                let price = parseFloat(row.querySelector(".price").innerText);
                let qty = parseInt(row.querySelector(".qty-input").value);
                total += price * qty;
            });
            document.getElementById("totalAmount").innerText = "Rs. " + total.toFixed(2);
        }
    </script>
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">🛒 Your Cart</h2>
    <form action="OrderServlet?action=PLACE_ORDER" method="post">
        <table class="table table-bordered">
            <thead class="table-light">
                <tr>
                    <th>Dish</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    double total = 0.0;
                    for (Menu item : groupedItems.values()) {
                        double itemTotal = item.getPrice() * item.getQuantity();
                        total += itemTotal;
                %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td class="price"><%= item.getPrice() %></td>
                    <td>
                        <input type="number" class="form-control qty-input" name="qty_<%= item.getId() %>" value="<%= item.getQuantity() %>" min="1" onchange="updateTotal()" required>
                    </td>
                    <td>
                        <a href="RemoveFromCartServlet?id=<%= item.getId() %>" class="btn btn-danger btn-sm">Remove</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <h5>Total: <span id="totalAmount">Rs. <%= total %></span></h5>

        <div class="mb-3">
            <label>Name:</label>
            <input type="text" name="customerName" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Contact:</label>
            <input type="text" name="customerPhone" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-success">Place Order</button>
        <a href="menu-list.jsp" class="btn btn-secondary">Back to Menu</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
