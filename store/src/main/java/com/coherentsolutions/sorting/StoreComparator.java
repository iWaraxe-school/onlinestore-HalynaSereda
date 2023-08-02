package com.coherentsolutions.sorting;


import com.coherentsolutions.domain.Product;

import java.util.Comparator;
import java.util.Map;



public class StoreComparator implements Comparator<Product> {
    Map<String, String> sortingMap;
    public StoreComparator (Map<String, String> sortingMap)
    {
        this.sortingMap = sortingMap;
    }
    @Override
    public int compare(Product o1, Product o2) {
        for (Map.Entry<String, String> entry : sortingMap.entrySet()) {
            String sortOrder = entry.getValue();
            String sortField = entry.getKey();
            int result = sortByField (sortField, o1, o2);
            if (result != 0 ) {
                if (sortOrder.equals("asc")) {
                    return result;
                } else if (sortOrder.equals("desc")) {
                    return result * -1;
                }
            }
        }

        return 0;
    }

    private int sortByField (String sortField, Product o1, Product o2) {
        switch (sortField) {
            case "name":
                return o2.getName().compareTo(o1.getName());
            case "price":
                return Double.compare(o1.getPrice(), o2.getPrice());
            case "rate":
                return Double.compare(o1.getRate(), o2.getRate());
            default:  {
                return 0;
            }
        }
}}
