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

        }
    }

    private void rendPageUserList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<User> users = UserModel.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(req, resp);
        } catch (SQLException e){
            resp.sendRedirect("/500");
            e.printStackTrace();
        }catch (IOException | ServletException e) {
            e.printStackTrace();
        }

    }
}
