package com.coherentsolutions.domain;


public class Product {

    private final String  name;
    private final double rate;
    private final double price;

    /**
     * Constructs a new Product using the provided ProductBuilder.
     * @param productBuilder The ProductBuilder to use for constructing the product.
     */
    public Product(ProductBuilder productBuilder) {
        this.name = productBuilder.name;
        this.price = productBuilder.price;
        this.rate = productBuilder.rate;
    }

    /**
     * Gets the name of the product.
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the product.
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the rate or rating of the product.
     * @return The rate of the product.
     */
    public double getRate() {
        return rate;
    }


    /**
     * Returns a string representation of the product, including its name, price, and rate.
     * @return The string representation of the product.
     */
    @Override
    public String toString() {
        return String.format("Name: '%s', Price: '%s', Rate: '%s'", name, price, rate);
    }

    /**
     * Builder class for constructing Product objects.
     */
    public static class ProductBuilder {
        private String name;
        private double price;
        private double rate;

        /**
         * Sets the name of the product.
         *
         * @param name The name of the product.
         * @return The ProductBuilder instance.
         * @throws IllegalArgumentException if the name is null or empty.
         */
        public ProductBuilder withName(String name) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be null or empty");
            }
            this.name = name;
            return this;
        }

        /**
         * Sets the price of the product.
         *
         * @param price The price of the product.
         * @return The ProductBuilder instance.
         * @throws IllegalArgumentException if the price is negative.
         */
        public ProductBuilder withPrice(double price) {
            if (price < 0) {
                throw new IllegalArgumentException("Product price cannot be negative");
            }
            this.price = price;
            return this;
        }

        /**
         * Sets the rate of the product.
         *
         * @param rate The rate of the product.
         * @return The ProductBuilder instance.
         * @throws IllegalArgumentException if the rate is not between 0 and 5.
         */
        public ProductBuilder withRate(double rate) {
            if (rate < 0 || rate > 5) {
                throw new IllegalArgumentException("Product rate must be between 0 and 5");
            }
            this.rate = rate;
            return this;
        }

        /**
         * Constructs a new Product instance with the provided attributes.
         *
         * @return The constructed Product instance.
         * @throws IllegalStateException if essential attributes (e.g., name) are not properly set.
         */
        public Product build() {
            if (name == null) {
                throw new IllegalStateException("Product name must be set");
            }
            if (price < 0) {
                throw new IllegalStateException("Product price must be set");
            }
            if (rate < 0 || rate > 5) {
                throw new IllegalStateException("Product rate must be between 0 and 5");
            }

            return new Product(this);
        }
    }
}
