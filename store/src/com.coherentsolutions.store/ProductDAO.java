package com.coherentsolutions.store;

import com.coherentsolutions.domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {
    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertProduct(Product product) throws SQLException {
        String insertProductSQL = "INSERT INTO products (NAME, PRICE, RATE) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductSQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getRate());
            preparedStatement.executeUpdate();
        }
    }
}