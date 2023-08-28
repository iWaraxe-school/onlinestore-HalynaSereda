package com.coherentsolutions.store;

import com.coherentsolutions.domain.Product;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Runnable class responsible for simulating the processing of an order for a product.
 */
public class CreateOrder implements Runnable {
    private final Product product;
    private final ConcurrentLinkedQueue<Product> purchasedGoods;
    private static final Logger logger = Logger.getLogger(CreateOrder.class.getName());

    /**
     * Constructs a CreateOrder instance with the given product and a collection to store purchased goods.
     *
     * @param product        The product for which the order will be processed.
     * @param purchasedGoods The collection to store the purchased goods.
     */
    public CreateOrder(Product product, ConcurrentLinkedQueue<Product> purchasedGoods) {
        this.product = product;
        this.purchasedGoods = purchasedGoods;
    }

    /**
     * Simulates the processing of an order for the product. The processing time is determined randomly.
     * The processed product is added to the collection of purchased goods.
     */
    @Override
    public void run() {
        int processingTime = ThreadLocalRandom.current().nextInt(1, 31);
        try {
            logger.info("Processing order for product: " + product.getName());
            // Simulate different processing outcomes (e.g., success, failure, delay)
            if (simulateOrderProcessing()) {
                TimeUnit.SECONDS.sleep(processingTime);
                purchasedGoods.offer(product);
                logger.info("Order processed for product: " + product.getName());
            } else {
                logger.warning("Order processing failed for product: " + product.getName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Simulates the outcome of order processing.
     *
     * @return true if order processing is successful, false otherwise.
     */
    private boolean simulateOrderProcessing() {
        // Simulate different outcomes based on business logic
        // For example, return true for successful processing and false for failure
        return ThreadLocalRandom.current().nextBoolean();
    }
}