package com.coherentsolutions.store;


import com.coherentsolutions.domain.Product;
import com.github.javafaker.Faker;
/**
 * Generates random products for different categories using the Faker library.
 */
public class RandomStorePopulator {
    public static Faker faker = new Faker();
    /**
     * Generates a random product based on the specified category name.
     *
     * @param categoryName The name of the category for which to generate the product.
     * @return A randomly generated product.
     * @throws IllegalArgumentException If the specified category name is not valid.
     */
    public Product generateProduct(String categoryName) {
        Product.ProductBuilder productBuilder = new Product.ProductBuilder();

        switch (categoryName) {
            case "FOOD":
                return productBuilder
                        .withName(faker.food().dish())
                        .withPrice(faker.number().randomDouble(2, 1, 100))
                        .withRate(faker.number().randomDouble(2, 1, 5))
                        .build();

            case "BOOK":
                return productBuilder
                        .withName(faker.book().title())
                        .withPrice(faker.number().randomDouble(2, 1, 100))
                        .withRate(faker.number().randomDouble(2, 1, 5))
                        .build();

            case "PHONE":
                return productBuilder
                        .withName(faker.commerce().productName())
                        .withPrice(faker.number().randomDouble(2, 1, 100))
                        .withRate(faker.number().randomDouble(2, 1, 5))
                        .build();

            default:
                throw new IllegalArgumentException("Unrecognized category: " + categoryName);
        }
    }
}