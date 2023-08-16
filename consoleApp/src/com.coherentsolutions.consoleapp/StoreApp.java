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
    Store onlineStore = Store.getInstance();
    StoreHelper storeHelper = new StoreHelper(onlineStore);
    storeHelper.fillStore();

    Map<String, Sorting> sortingMap = XMLParser.getSortInOrder();

    List<Product> products = new ArrayList<>();
    for (Category category : onlineStore.getCategoryList()) {
        products.addAll(category.getProductList());
    }

    while (true) {
        // Display menu options
        System.out.println("Select an option:");
        System.out.println("1. Sort products");
        System.out.println("2. Show top 5 most expensive items");
        System.out.println("3. Exit");

        int choice = getIntInput(scanner);

        switch (choice) {
            case 1:
                performSorting(scanner, products, sortingMap);
                break;
            case 2:
                displayTopNMostExpensiveItems(products, 5);
                break;
            case 3:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}

    private static int getIntInput(Scanner scanner) {
        int choice = -1; // Default value to indicate an invalid choice

        while (true) {
            try {
                choice = scanner.nextInt();
                break; // Break the loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return choice;
    }

    private static void performSorting(Scanner scanner, List<Product> products, Map<String, Sorting> sortingMap) {
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

        // Use the common method to sort and display products
        sortAndDisplayProducts(products, chosenField, sortingOrder);
    }

    private static void displayTopNMostExpensiveItems(List<Product> products, int topN) {
        List<Product> topItems = getTopNItems(products, topN);

        System.out.println("Top " + topN + " most expensive items:");
        for (Product product : topItems) {
            System.out.println(product);
        }
    }

    private static List<Product> getTopNItems(List<Product> products, int topN) {
        // Create a StoreComparator for descending price sorting
        StoreComparator storeComparator = new StoreComparator("price", Sorting.DESC);

        // Sort products using storeComparator
        Collections.sort(products, storeComparator);

        // Return the top N most expensive items
        return products.subList(0, Math.min(topN, products.size()));
    }

    private static void sortAndDisplayProducts(List<Product> products, String fieldName, Sorting sortingOrder) {
        List<Product> sortedProducts = sortProducts(products, fieldName, sortingOrder);

        // Display sorted products
        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

    private static List<Product> sortProducts(List<Product> products, String fieldName, Sorting sortingOrder) {
        // Create a StoreComparator with the chosen field and sorting order
        StoreComparator storeComparator = new StoreComparator(fieldName, sortingOrder);

        // Sort products using storeComparator
        List<Product> sortedProducts = new ArrayList<>(products);
        Collections.sort(sortedProducts, storeComparator);

        return sortedProducts;
    }
}