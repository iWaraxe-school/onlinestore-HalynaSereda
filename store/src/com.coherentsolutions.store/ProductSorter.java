package com.coherentsolutions.store;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.sorting.Sorting;
import com.coherentsolutions.sorting.StoreComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSorter {

    public static List<Product> sortProducts(List<Product> products, String fieldName, Sorting sortingOrder) {
        // Create a StoreComparator with the chosen field and sorting order
        StoreComparator storeComparator = new StoreComparator(fieldName, sortingOrder);

        // Sort products using storeComparator
        List<Product> sortedProducts = new ArrayList<>(products);
        Collections.sort(sortedProducts, storeComparator);

        return sortedProducts;
    }

    public static List<Product> getTopNItems(List<Product> products, int topN) {
        // Create a StoreComparator for descending price sorting
        StoreComparator storeComparator = new StoreComparator("price", Sorting.DESC);

        // Sort products using storeComparator
        Collections.sort(products, storeComparator);

        // Return the top N most expensive items
        return products.subList(0, Math.min(topN, products.size()));
    }
}