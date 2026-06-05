package com.codegym.demomvc.controllers;

import com.codegym.demomvc.models.DatabaseModel;
import com.codegym.demomvc.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AuthController", urlPatterns = "/auth/*")
public class AuthController extends HttpServlet {
    private UserModel userModel;

    @Override
    public void init() throws ServletException {
        userModel = new UserModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String url = req.getPathInfo();
       if (url == null){
           url = "";
       }
       switch (url) {
           case "/login":
               renderLoginPage(req, resp);
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
            case "/login":
                handleLogin(req, resp);
                break;

        }
    }

    private void renderLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lay thong tin username, password
        String uname = request.getParameter("username");
        String password = request.getParameter("password");
        // truyen username, password xuong model -> tim trong csdl
        try {
            boolean isAccount = userModel.checkAccount(uname, password);
            if (isAccount) {
                response.sendRedirect("/home");
            } else {
                response.sendRedirect("/auth/login?error=true");
            }
        }catch (Exception e) {
            response.sendRedirect("/500");
            e.printStackTrace();
        }

        // chuyen trang neu thanh cong
    }
}
