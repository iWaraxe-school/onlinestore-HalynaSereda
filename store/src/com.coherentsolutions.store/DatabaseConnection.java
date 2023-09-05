package com.coherentsolutions.store;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/onlinestore";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2019888!Gs";

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);

        // Other configuration options can be set here

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Add a method to close the data source when shutting down
    public static void closeDataSource() {
        dataSource.close();
    }


}