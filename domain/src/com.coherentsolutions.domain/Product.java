package com.coherentsolutions.domain;

public class Product {

    public final String name;
    public int rate;
    public double price;

    public Product(String name, int rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }}