package com.coherentsolutions.store;

import com.coherentsolutions.domain.Product;
import com.coherentsolutions.sorting.Sorting;
import com.coherentsolutions.sorting.StoreComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Utility class for sorting and selecting products.
 */
public class ProductSorter {

    /**
     * Sorts a list of products based on the specified field and sorting order.
     *
     * @param products     The list of products to be sorted.
     * @param fieldName    The name of the field to sort by (e.g., "name", "price").
     * @param sortingOrder The sorting order (ASC or DESC).
     * @return A new list containing the sorted products.
     */
    public static List<Product> sortProducts(List<Product> products, String fieldName, Sorting sortingOrder) {
        // Create a StoreComparator with the chosen field and sorting order
        StoreComparator storeComparator = new StoreComparator(fieldName, sortingOrder);

        // Sort products using storeComparator
        List<Product> sortedProducts = new ArrayList<>(products);
        Collections.sort(sortedProducts, storeComparator);

        return sortedProducts;
    }

    /**
     * Returns the top N most expensive items from the given list of products.
     *
     * @param products The list of products to select from.
     * @param topN     The number of top items to return.
     * @return A list containing the top N most expensive items.
     */
    public static List<Product> getTopNItems(List<Product> products, int topN) {
        // Create a StoreComparator for descending price sorting
        StoreComparator storeComparator = new StoreComparator("price", Sorting.DESC);

        // Sort products using storeComparator
        Collections.sort(products, storeComparator);

        // Return the top N most expensive items
        return products.subList(0, Math.min(topN, products.size()));
    }
}