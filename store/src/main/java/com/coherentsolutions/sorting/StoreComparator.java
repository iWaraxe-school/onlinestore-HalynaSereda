package com.coherentsolutions.sorting;


import com.coherentsolutions.domain.Product;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;


public class StoreComparator implements Comparator<Product> {private final String fieldName;
    private final Sorting sortingOrder;

    public StoreComparator(String fieldName, Sorting sortingOrder) {
        this.fieldName = fieldName;
        this.sortingOrder = sortingOrder;
    }

    @Override
    public int compare(Product o1, Product o2) {
        try {
            // Use reflection to get the getter method for the field
            Method getterMethod = Product.class.getMethod("get" + capitalize(fieldName));

            // Invoke the getter method on the products
            Object fieldValue1 = getterMethod.invoke(o1);
            Object fieldValue2 = getterMethod.invoke(o2);

            // Compare the field values based on the sorting order
            if (sortingOrder == Sorting.ASC) {
                return ((Comparable) fieldValue1).compareTo(fieldValue2);
            } else {
                return ((Comparable) fieldValue2).compareTo(fieldValue1);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error while sorting products: " + e.getMessage());
        }
    }

    // Helper method to capitalize the first letter of a string
    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}