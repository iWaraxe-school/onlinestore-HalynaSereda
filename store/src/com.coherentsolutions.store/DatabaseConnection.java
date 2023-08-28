package com.coherentsolutions.store;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/onlinestore";
    private static final String USER = "HalynaSereda";
    private static final String PASSWORD = "2019888!Gs";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}