package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import com.coherentsolutions.sorting.Sorting;
import com.coherentsolutions.store.*;
import com.coherentsolutions.xml.XMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;

public class StoreApp {
    private static final Logger logger = LoggerFactory.getLogger(StoreApp.class);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Create a Scanner for user input
        try (Connection connection = DatabaseConnection.getConnection()) {
            Store onlineStore = Store.getInstance();  // Create Store instance here
            StoreHelper storeHelper = new StoreHelper(onlineStore);  // Pass the Store instance
            storeHelper.fillStore();

    Map<String, Sorting> sortingMap = XMLParser.getSortInOrder();

    List<Product> products = new ArrayList<>();
    for (Category category : onlineStore.getCategoryList()) {
        products.addAll(category.getProductList());
    }
        // Create a thread-safe collection to store purchased goods
        ConcurrentLinkedQueue<Product> purchasedGoods = new ConcurrentLinkedQueue<>();

        // Specify the maximum number of items to retain in the purchased goods collection
        int maxItemsToRetain = 100; // You can adjust this value as needed

        // Create a thread pool for order processing
        int numProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService orderProcessingThreadPool = Executors.newFixedThreadPool(numProcessors);

        // Create a scheduled executor service for cleaning up purchased goods
        ScheduledExecutorService cleanupScheduler = Executors.newSingleThreadScheduledExecutor();

        // Schedule the ClearPurchasedGoodsTask to run periodically (every 2 minutes)
        cleanupScheduler.scheduleAtFixedRate(new ClearPurchasedGoods(purchasedGoods, maxItemsToRetain), 0, 2, TimeUnit.MINUTES);
        // Graceful shutdown mechanism
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down the application...");


            // Shut down the thread pools
            orderProcessingThreadPool.shutdown();
            cleanupScheduler.shutdown();

            try {
                // Wait for thread pools to terminate gracefully
                if (!orderProcessingThreadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                    logger.warn("Order processing thread pool did not terminate gracefully. Forcing shutdown.");
                    orderProcessingThreadPool.shutdownNow();
                }
                if (!cleanupScheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                    logger.warn("Cleanup scheduler did not terminate gracefully. Forcing shutdown.");
                    cleanupScheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                logger.error("Application shutdown process interrupted.", e);
            }

            logger.info("Application shutdown complete.");
        }));


    while (true) {
        // Display menu options
        System.out.println("Select an option:");
        System.out.println("1. Sort products");
        System.out.println("2. Show top 5 most expensive items");
        System.out.println("3. Exit");

        int choice = getIntInput(scanner);

        try {

        switch (choice) {
            case 1:
                performSorting(scanner, products, sortingMap);
                break;
            case 2:
                displayTopNMostExpensiveItems(products, 5);
                break;
            case 3:
                // Shutdown the thread pool and cleanup scheduler before exiting
                orderProcessingThreadPool.shutdown();
                cleanupScheduler.shutdown();
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    } catch (Exception e) {
            // Handle unexpected exceptions gracefully
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }}
 }
           catch (SQLException e) {
               logger.error("Error establishing database connection: " + e.getMessage());
           } finally {
               logger.info("Goodbye!");
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
        List<Product> sortedProducts = ProductSorter.sortProducts(products, chosenField, sortingOrder);
        displayProducts(sortedProducts);
    }
    private static void displayTopNMostExpensiveItems(List<Product> products, int topN) {
        List<Product> topItems = ProductSorter.getTopNItems(products, topN);

        System.out.println("Top " + topN + " most expensive items:");
        displayProducts(topItems);
    }

    private static void displayProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }


    private static void processOrder(Product product, ConcurrentLinkedQueue<Product> purchasedGoods, ExecutorService threadPool) {
        // Create and submit a CreateOrderTask to the thread pool
        Runnable orderTask = new CreateOrder(product, purchasedGoods);
        threadPool.submit(orderTask);
    }
}