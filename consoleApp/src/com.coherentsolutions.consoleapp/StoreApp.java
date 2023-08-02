package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import com.coherentsolutions.sorting.StoreComparator;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreHelper;
import com.coherentsolutions.xml.XMLParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);
        storeHelper.fillStore();

        Map<String, String> sortingMap;
        try {
            sortingMap = XMLParser.getSortInOrder();
        } finally {

        }

        StoreComparator storeComparator = new StoreComparator(sortingMap);

        for (Category category : onlineStore.getCategoryList()) {
            List<Product> products = new ArrayList<>(category.getProductList());
            products.sort(storeComparator);

            System.out.println("Category: " + category.getName());
            for (Product product : products) {
                System.out.println(product);
            }
            System.out.println();
        }
    }
}



