package com.codegym.demomvc.controllers;


import com.codegym.demomvc.entities.User;
import com.codegym.demomvc.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/users/*")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        if (url == null){
            url = "";
        }
        switch (url) {
            case "/":
            case "":
                rendPageUserList(req, resp);
                break;
            case "/create":
                rendPageCreateUser(req, resp);
                break;
            case "/delete":
                try {
                    handleDeleteUser(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        if (url == null){
            url = "";
        }
        switch (url) {
            case "/store":
                try {
                    processCreateUser(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void rendPageUserList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<User> users = UserModel.getAllUsers();
            req.setAttribute("data", users);
            req.getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(req, resp);
        } catch (SQLException e){
            resp.sendRedirect("/500");
            e.printStackTrace();
        }catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void rendPageCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(req, resp);
    }

    private void processCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // Lay data tu request
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // khoi tao 1 object
        User user = new User(username, email, password);
        // gui data xuong model
        UserModel.addUser(user);
        resp.sendRedirect("/users");
    }

    private void handleDeleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // lay id tu request
        int id = Integer.parseInt(req.getParameter("id"));
        UserModel.deleteUserById(id);
        resp.sendRedirect("/users");
    }
}
