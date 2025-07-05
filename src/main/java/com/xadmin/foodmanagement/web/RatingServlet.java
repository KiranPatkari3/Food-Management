package com.xadmin.foodmanagement.web;

import com.xadmin.foodmanagement.bean.Rating;
import com.xadmin.foodmanagement.bean.User;
import com.xadmin.foodmanagement.dao.RatingDao;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/rate")
public class RatingServlet extends HttpServlet {
    private RatingDao ratingDao;

    public void init() {
        ratingDao = new RatingDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("📥 [RatingServlet] POST /rate called");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String starsParam = request.getParameter("stars");
        String menuIdParam = request.getParameter("menuId");

        // Debug log to check what's being received
        System.out.println("DEBUG: starsParam = " + starsParam);
        System.out.println("DEBUG: menuIdParam = " + menuIdParam);

        if (starsParam == null || starsParam.isEmpty() || menuIdParam == null || menuIdParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            response.getWriter().write("Missing rating or menuId");
            return;
        }

        if (user == null) {
            System.out.println("❌ User not logged in");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
            response.getWriter().write("User not logged in");
            return;
        }

        try {
            int stars = Integer.parseInt(starsParam);
            int menuId = Integer.parseInt(menuIdParam);

            System.out.println("➡️  menuId = " + menuId);
            System.out.println("➡️  stars = " + stars);
            System.out.println("➡️  userId = " + user.getId());

            Rating rating = new Rating(user.getId(), menuId, stars);
            ratingDao.saveRating(rating);

            System.out.println("✅ Rating saved successfully");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("success");

        } catch (Exception e) {
            System.out.println("❌ Exception occurred:");
            e.printStackTrace(); // This will print error in console
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            response.getWriter().write("error: " + e.getMessage());
        }
    }
}
