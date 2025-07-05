package com.xadmin.foodmanagement.web;

import com.xadmin.foodmanagement.bean.User;
import com.xadmin.foodmanagement.dao.UserDao;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action == null ? "LIST" : action) {
                case "NEW":
                    showNewForm(request, response);
                    break;
                case "INSERT":
                    insertUser(request, response);
                    break;
                case "DELETE":
                    deleteUser(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> listUsers = userDao.selectAllUsers();

        // 🔍 Debug output
        System.out.println("🧾 Users found: " + listUsers.size());
        for (User user : listUsers) {
            System.out.println("👉 " + user.getId() + ": " + user.getUsername());
        }

        request.setAttribute("userList", listUsers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // get password from form
        String role = request.getParameter("role"); // default or from form

        if (role == null || role.trim().isEmpty()) {
            role = "user";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        userDao.insertUser(user);
        response.sendRedirect("UserServlet");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(id);
        response.sendRedirect("UserServlet");
    }
}
