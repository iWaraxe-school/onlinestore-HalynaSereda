package com.coherentsolutions.sorting;


import com.coherentsolutions.domain.Product;

import java.util.*;


public class StoreComparator implements Comparator<Product> {
    Map<String, String> sortingMap;
    public StoreComparator (Map<String, String> sortingMap)
    {

        this.sortingMap = sortingMap;
    }

    @Override
    public int compare(Product o1, Product o2) {
        // Check if sorting by price in descending order is requested
        String sortByPriceDesc = sortingMap.get("price");
        if (sortByPriceDesc != null && sortByPriceDesc.equals("desc")) {
            return Double.compare(o2.getPrice(), o1.getPrice()); // Descending order by price
        } else {
            // Default sorting based on the sortingMap
            for (Map.Entry<String, String> entry : sortingMap.entrySet()) {
                String sortOrder = entry.getValue();
                String sortField = entry.getKey();
                int result = sortByField(sortField, o1, o2);
                if (result != 0) {
                    if (sortOrder.equals("asc")) {
                        return result;
                    } else if (sortOrder.equals("desc")) {
                        return result * -1;
                    }
                }
            }
            return 0;
        }
    }

    private int sortByField(String sortField, Product o1, Product o2) {
        switch (sortField) {
            case "name":
                return o1.getName().compareTo(o2.getName());
            case "price":
                return Double.compare(o1.getPrice(), o2.getPrice());
            case "rate":
                return Double.compare(o2.getRate(), o1.getRate());
            default:
                return 0;
        }
    }

    public List<Product> findMostExpensiveItems(List<Product> products, int i) {
        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Collections.reverseOrder(this));

        int totalItems = Math.min(5, sortedProducts.size());

        return sortedProducts.subList(0, totalItems);
    }
}