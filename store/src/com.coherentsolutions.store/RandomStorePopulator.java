package com.coherentsolutions.store;


import com.coherentsolutions.domain.Product;
import com.github.javafaker.Faker;
public class RandomStorePopulator {
    public static Faker faker = new Faker();

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
                throw new IllegalArgumentException("No such category");
        }
    }
}