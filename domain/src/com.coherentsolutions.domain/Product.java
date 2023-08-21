package com.coherentsolutions.domain;


public class Product {

    private String name;
    private double rate;
    private double price;

    /**
     * Constructs a new Product with the specified name, rate, and price.
     * @param name The name of the product.
     * @param rate The rate or rating of the product.
     * @param price The price of the product.
     */
    public Product(String name, double rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

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
         * @param name The name of the product.
         * @return The ProductBuilder instance.
         */
        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the price of the product.
         * @param price The price of the product.
         * @return The ProductBuilder instance.
         */
        public ProductBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        /**
         * Sets the rate of the product.
         * @param rate The rate of the product.
         * @return The ProductBuilder instance.
         */
        public ProductBuilder withRate(double rate) {
            this.rate = rate;
            return this;
        }

        /**
         * Constructs a new Product using the configured ProductBuilder.
         * @return The constructed Product.
         */
        public Product build() {
            return new Product(this);
        }
    }
}
