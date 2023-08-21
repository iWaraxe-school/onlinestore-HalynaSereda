package com.coherentsolutions.store;

import com.coherentsolutions.domain.Product;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

    public class CreateOrder implements Runnable {
        private final Product product; // The product for which the order is being created
        private final ConcurrentLinkedQueue<Product> purchasedGoods; // Thread-safe collection to store purchased goods

        // Constructor to initialize the CreateOrderTask with the product and purchasedGoods collection
        public CreateOrder(Product product, ConcurrentLinkedQueue<Product> purchasedGoods) {
            this.product = product;
            this.purchasedGoods = purchasedGoods;
        }

        @Override
        public void run() {
            // Generate a random processing time for the order (1 to 30 seconds)
            int processingTime = ThreadLocalRandom.current().nextInt(1, 31);
            try {
                System.out.println("Processing order for product: " + product.getName());
                // Simulate order processing time
                TimeUnit.SECONDS.sleep(processingTime);
                // Add the processed product to the purchasedGoods collection
                purchasedGoods.offer(product);
                System.out.println("Order processed for product: " + product.getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
