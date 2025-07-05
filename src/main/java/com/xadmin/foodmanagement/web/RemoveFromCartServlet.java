package com.xadmin.foodmanagement.web;

import com.xadmin.foodmanagement.bean.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(menu -> menu.getId() == id);
        }

        session.setAttribute("message", "Item removed from cart.");
        response.sendRedirect("cart.jsp");
    }
}
