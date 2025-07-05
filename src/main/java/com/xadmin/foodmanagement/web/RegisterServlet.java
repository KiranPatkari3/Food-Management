package com.xadmin.foodmanagement.web;

import com.xadmin.foodmanagement.bean.User;
import com.xadmin.foodmanagement.dao.UserDao;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String username = request.getParameter("username");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (username == null || email == null || password == null ||
        	    username.isEmpty() || email.isEmpty() || password.isEmpty()) {
        	    request.setAttribute("errorMessage", "All fields are required!");
        	    request.getRequestDispatcher("register.jsp").forward(request, response);
        	    return;
        	}

        	User newUser = new User();
        	newUser.setUsername(username); // ✅ correct
        	newUser.setEmail(email);
        	newUser.setPassword(password);
        	newUser.setRole("user");

        userDao.insertUser(newUser);
        response.sendRedirect("login.jsp?msg=registered");
    }
}
