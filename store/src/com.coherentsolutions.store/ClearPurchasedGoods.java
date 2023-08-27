package com.coherentsolutions.store;

import com.coherentsolutions.domain.Product;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
/**
 * A task that performs cleanup on the purchased goods collection at regular intervals.
 */
public class ClearPurchasedGoods implements Runnable {
    private final ConcurrentLinkedQueue<Product> purchasedGoods;
    private final int maxItemsToRetain;
    private static final Logger logger = Logger.getLogger(ClearPurchasedGoods.class.getName());

    /**
     * Constructs a ClearPurchasedGoods task with the specified collection of purchased goods and maximum items to retain.
     *
     * @param purchasedGoods The collection of purchased goods to be managed.
     * @param maxItemsToRetain The maximum number of items to retain in the purchased goods collection.
     */
    public ClearPurchasedGoods(ConcurrentLinkedQueue<Product> purchasedGoods, int maxItemsToRetain) {
        this.purchasedGoods = purchasedGoods;
        this.maxItemsToRetain = maxItemsToRetain;
    }

    /**
     * Performs cleanup on the purchased goods collection, retaining a specified number of most recent items.
     * This method is intended to be executed at regular intervals.
     */
    @Override
    public void run() {
        logger.info("Performing cleanup on purchased goods");

        while (purchasedGoods.size() > maxItemsToRetain) {
            purchasedGoods.poll();
        }

        logger.info("Purchased goods cleanup complete");
    }
}
