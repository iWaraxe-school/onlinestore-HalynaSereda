package com.coherentsolutions.domain;

import java.util.ArrayList;

public class Category {
    private final String name;
    private List<Product> productList;

    public Category (String name){
        this.name = name;
        this.productList = new ArrayList<>();
    }
}