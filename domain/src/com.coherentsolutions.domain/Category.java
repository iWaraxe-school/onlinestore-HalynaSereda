package com.coherentsolutions.domain;

import java.util.List;
import java.util.ArrayList;
public class Category {
    private final Categories name;
    private final List<Product> productList;

    public Category (Categories name){
        this.name = name;
        this.productList = new ArrayList<Product>();
    }



    public String getName() {
        return String.valueOf(name);
    }



    public void addProductToCatedory(Product product) {
    }
}