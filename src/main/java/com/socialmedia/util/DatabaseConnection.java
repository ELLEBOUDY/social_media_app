package com.socialmedia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/social_media";
    private static final String USER = "root";
    private static final String PASSWORD = "DB PASSOWRD";

    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Database Connection Failed!");
            e.printStackTrace();
            throw e;
        }
    }
}