package com.coherentsolutions.store;


public class RandomStorePopulator {
   public static Faker faker = new Faker();

    public String getProductName(String categoryName) {
        switch (categoryName) {
            case "FOOD":
                return faker.food().name();
                return faker.food().rate();
                return faker.food().price();

            case "BOOK":
                return faker.book().name();
                return faker.book().rate();
                return faker.book().price();

            case "PHONE":
                return faker.phone().name();
                return faker.phone().rate();
                return faker.phone().price();

            default: return "No items in selected category";

        }

    }}