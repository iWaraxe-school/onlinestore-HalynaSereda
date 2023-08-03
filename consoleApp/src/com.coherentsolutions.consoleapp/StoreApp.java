package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import com.coherentsolutions.sorting.StoreComparator;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper;
import com.coherentsolutions.xml.XMLParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StoreApp {
    public static void main(String[] args) {
        // Create and populate the store with categories and products
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);
        storeHelper.fillStore();

        // Find all products from the store's categories and store them in a single list
        List<Product> products = new ArrayList<>();
        for (Category category : onlineStore.getCategoryList()) {
            products.addAll(category.getProductList());
        }
        // Get the sorting map from the XML parser

        Map<String, String> sortingMap;
        try {
            sortingMap = XMLParser.getSortInOrder();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }

        // Sort the entire store based on the sorting map using StoreComparator
        StoreComparator storeComparator = new StoreComparator(sortingMap);
        Collections.sort(products, storeComparator);


        // Find the top 5 most expensive items using the same StoreComparator
        List<Product> topFiveMostExpensiveItems = storeComparator.findMostExpensiveItems (products, 5);

        // Print the top 5 most expensive items
        System.out.println("Top 5 most expensive items:");
        for (Product product : topFiveMostExpensiveItems) {
            System.out.println(product);
        }
    }
}






