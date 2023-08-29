package com.coherentsolutions.store;

import com.coherentsolutions.domain.Product;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class responsible for generating random products to populate the store.
 */
public class RandomStorePopulator {
    private static final Logger logger = LoggerFactory.getLogger(RandomStorePopulator.class);
    private static final Faker faker = new Faker();
    private final Connection connection; // Injected connection

    public RandomStorePopulator(Connection connection) {
        this.connection = connection;
    }

    /**
     * Generates a random product for the given category name and inserts it into the database.
     *
     * @throws SQLException if a database error occurs
     */
    public Product generateProduct(String name) {
        String randomName = faker.name().name();
        double randomPrice = faker.number().randomDouble(2, 1, 100);
        double randomRate = faker.number().randomDouble(2, 1, 5);

        Product.ProductBuilder productBuilder = new Product.ProductBuilder()
                .withName(randomName)
                .withPrice(randomPrice)
                .withRate(randomRate);

        return productBuilder.build();
    }


    public static void insertProductIntoDatabase(Product product, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO products (NAME, PRICE, RATE) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setDouble(3, product.getRate());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Product '{}' added to the database.", product.getName());
            } else {
                logger.error("Failed to add product '{}' to the database.", product.getName());
            }
        } catch (SQLException e) {
            logger.error("Error storing product in the database: " + e.getMessage());
        }
    }}