package com.coherentsolutions.domain;


public class Product {

    private String name;
    private double rate;
    private double price;

    public Product(String name, double rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public Product(ProductBuilder productBuilder) {
        this.name = productBuilder.name;
        this.price = productBuilder.price;
        this.rate = productBuilder.rate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRate() {
        return rate;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return String.format("Name: '%s', Price: '%s', Rate: '%s'", name, price, rate);}

    public static class ProductBuilder {
        private String name;
        private double price;
        private double rate;

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withRate(double rate) {
            this.rate = rate;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}


