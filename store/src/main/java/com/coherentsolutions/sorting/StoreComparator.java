package com.coherentsolutions.sorting;


import com.coherentsolutions.domain.Product;

import java.util.*;


public class StoreComparator implements Comparator<Product> {
    private String sortingField; // Field to be sorted (e.g., "name", "price", "rate")
    private Sorting sortingOrder; // Sorting order (asc or desc)

    public StoreComparator(String sortingField, Sorting sortingOrder) {
        this.sortingField = sortingField;
        this.sortingOrder = sortingOrder;
    }

    @Override
    public int compare(Product o1, Product o2) {
        int result;
        switch (sortingField) {
            case "name":
                result = o1.getName().compareTo(o2.getName());
                break;
            case "price":
                result = Double.compare(o1.getPrice(), o2.getPrice());
                break;
            case "rate":
                result = Double.compare(o1.getRate(), o2.getRate());
                break;
            default:
                result = 0;
                break;
        }

        return sortingOrder == Sorting.ASC ? result : -result; // Apply sorting order
    }
    }