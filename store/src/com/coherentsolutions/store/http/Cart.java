package com.coherentsolutions.store.http;

import com.coherentsolutions.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items;

    public Cart() {
        items = new ArrayList<>();
    }

    // Add a product to the cart
    public void addProduct(Product product) {
        items.add(product);
    }

    // Remove a product from the cart
    public void removeProduct(Product product) {
        items.remove(product);
    }

    // Get the contents of the cart
    public List<Product> getItems() {
        return items;
    }

    // Calculate the total cost of items in the cart
    public double calculateTotalCost() {
        double total = 0.0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // Check if the cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }
}