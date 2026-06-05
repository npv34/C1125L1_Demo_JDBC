package com.codegym.demomvc.models;

import com.codegym.demomvc.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private static Connection conn = DatabaseModel.getConnection();

    public UserModel() {

    }

    public boolean checkAccount(String username, String password) throws SQLException {
        // b1: Viet cau lenh SQL
        String sql = "select count(*) from users where username = ? and password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        // thuc thi truy van
        ResultSet rs = preparedStatement.executeQuery();
        // xu ly du lieu tra ve
        return rs.next() && rs.getInt(1) > 0;
    }

    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            User user = new User(id, username);
            users.add(user);
        }
        return users;
    }
}
