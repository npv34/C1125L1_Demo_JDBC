package com.codegym.demomvc.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseModel {
    private static String url = "jdbc:mysql://localhost:3306/library";
    private static String username = "root";
    private static String password = "123456@Abc";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection conn = null;
        // dang ky driver cho dirver manager
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
