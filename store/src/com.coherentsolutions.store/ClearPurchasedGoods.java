package com.coherentsolutions.store;

import com.coherentsolutions.domain.Product;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ClearPurchasedGoods implements Runnable {
    private final ConcurrentLinkedQueue<Product> purchasedGoods; // Thread-safe collection to store purchased goods

    // Constructor to initialize the ClearPurchasedGoodsTask with the purchasedGoods collection
    public ClearPurchasedGoods(ConcurrentLinkedQueue<Product> purchasedGoods) {
        this.purchasedGoods = purchasedGoods;
    }

    @Override
    public void run() {
        System.out.println("Cleaning up purchased goods");
        purchasedGoods.clear(); // Clear the purchased goods collection
        System.out.println("Purchased goods cleaned up");
    }
}