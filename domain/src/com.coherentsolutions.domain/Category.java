package com.coherentsolutions.domain;

import java.util.ArrayList;
import java.util.List;
public class Category {
    private final Categories name;
    private final List<Product> productList;

    public Category (Categories name){
        this.name = name;
        this.productList = new ArrayList<>();
    }



    public String getName() {
        return String.valueOf(name);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProductToCategory(Product product) {
        productList.add(product);
    }
    @Override
    public String toString() {
        StringBuilder categoryStr = new StringBuilder("Category: " + this.name + "\n");
        for(Product product : productList) {
            categoryStr.append(product.toString()).append("\n");
        }
        return categoryStr.toString();
    }


}