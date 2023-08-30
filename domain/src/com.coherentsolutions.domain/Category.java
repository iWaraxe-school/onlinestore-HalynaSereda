package com.coherentsolutions.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Category {

    private final Categories name;
    private final List<Product> productList;

    /**
     * Constructs a new Category with the specified name.
     * @param name The name of the category.
     */
    public Category(Categories name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }

    /**
     * Gets the name of the category.
     * @return The name of the category.
     */
    public String getName() {
        return String.valueOf(name);
    }

    /**
     * Gets the list of products in the category.
     * @return The list of products in the category.
     */
    public synchronized List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    /**
     * Adds a product to the category's product list.
     * @param product The product to be added.
     */
    public synchronized void  addProductToCategory(Product product) {
        productList.add(product);
    }

    /**
     * Returns a string representation of the category, including its name and products.
     * @return The string representation of the category.
     */
    @Override
    public String toString() {
        StringBuilder categoryStr = new StringBuilder("Category: " + this.name + "\n");
        for (Product product : productList) {
            categoryStr.append(product.toString()).append("\n");
        }
        return categoryStr.toString();
    }
}