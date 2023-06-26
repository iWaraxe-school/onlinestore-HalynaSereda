package com.coherentsolutions.store;


public class RandomStorePopulator {
   public static Faker faker = new Faker();

    public String getProductName(String categoryName) {
        switch (categoryName) {
            case "FOOD":
                return faker.food().fp();

            case "BOOK":
                return faker.book().fp();


            case "PHONE":
                return faker.phone().fp();


            default: return "No items in selected category";

        }

    }}