package com.coherentsolutions.store;


import com.coherentsolutions.domain.Product;
import com.github.javafaker.Faker;

public class RandomStorePopulator {
    public static Faker faker = new Faker();

    public Product generateProduct(String categoryName) {
        switch (categoryName) {
            case "FOOD":
                return new Product(faker.food().ingredient(), faker.number().randomDouble(2, 1, 5), faker.number().randomDouble(2, 1, 100));

            case "BOOK":
                return new Product(faker.book().title(), faker.number().randomDouble(2, 1, 5), faker.number().randomDouble(2, 1, 100));

            case "PHONE":
                return new Product(faker.commerce().productName(), faker.number().randomDouble(2, 1, 5), faker.number().randomDouble(2, 1, 100));

            default:
                throw new IllegalArgumentException("No such category");
        }
    }
}