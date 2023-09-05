package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryDAO {

    private final Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertCategory(Category category) throws SQLException {
        String query = "INSERT INTO categories (name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        }
    }

}