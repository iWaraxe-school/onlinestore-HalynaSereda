package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import com.coherentsolutions.sorting.Sorting;
import com.coherentsolutions.sorting.StoreComparator;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper;
import com.coherentsolutions.xml.XMLParser;

import java.util.*;

public class StoreApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Create a Scanner for user input
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);
        storeHelper.fillStore();

        Map<String, Sorting> sortingMap = XMLParser.getSortInOrder();

        List<Product> products = new ArrayList<>();
        for (Category category : onlineStore.getCategoryList()) {
            products.addAll(category.getProductList());
        }

        // Main loop for user interaction
        while (true) {
            // Display menu options
            System.out.println("Select an option:");
            System.out.println("1. Sort products");
            System.out.println("2. Show top 5 most expensive items");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    performSorting(scanner, products, sortingMap);
                    break;
                case 2:
                    displayTopFiveMostExpensiveItems(products);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    // Method to handle sorting based on user input
    private static void performSorting(Scanner scanner, List<Product> products,
                                       Map<String, Sorting> sortingMap) {
        // Ask the user to choose a field for sorting
        System.out.println("Choose a field for sorting:");
        for (String fieldName : sortingMap.keySet()) {
            System.out.println(fieldName);
        }
        String chosenField = scanner.next();

        // Check if the chosen field exists in the sortingMap
        if (!sortingMap.containsKey(chosenField)) {
            System.out.println("Invalid field choice.");
            return;
        }

        // Get the sorting order from the sortingMap
        Sorting sortingOrder = sortingMap.get(chosenField);

        // Create a StoreComparator with the chosen field and sorting order
        StoreComparator storeComparator = new StoreComparator(chosenField, sortingOrder);

        // Sort products using storeComparator
        Collections.sort(products, storeComparator);

        // Display sorted products
        for (Product product : products) {
            System.out.println(product);
        }
    }

    // Method to display the top 5 most expensive items
    private static void displayTopFiveMostExpensiveItems(List<Product> products) {
        // Create a StoreComparator for descending price sorting
        StoreComparator storeComparator = new StoreComparator("price", Sorting.DESC);

        // Sort products using storeComparator
        Collections.sort(products, storeComparator);

        // Display the top 5 most expensive items
        int totalItems = Math.min(5, products.size());
        List<Product> topItems = products.subList(0, totalItems);

        System.out.println("Top 5 most expensive items:");
        for (Product product : topItems) {
            System.out.println(product);
        }
    }
}