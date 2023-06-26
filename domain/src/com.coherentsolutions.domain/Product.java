package com.coherentsolutions.domain;

public class Product {

    public String name;
    public double rate;
    public double price;

    public Product(String name, double rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }
    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    public double getRate() {
        return (double) rate;
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

    public  FakerProduct {
    String fp = name + rate +price;

    }
}
