package com.coherentsolutions.sorting;


import com.coherentsolutions.domain.Product;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Comparator class for sorting products based on a specified field and sorting order.
 */
public class StoreComparator implements Comparator<Product> {
    private final String fieldName;
    private final Sorting sortingOrder;
    private static final Logger LOGGER = Logger.getLogger(StoreComparator.class.getName());

    /**
     * Constructs a StoreComparator instance with the given field name and sorting order.
     *
     * @param fieldName    The name of the field to be used for comparison.
     * @param sortingOrder The sorting order (ASC or DESC).
     */
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
        } catch (Exception e) {
            LOGGER.severe("An error occurred while comparing products: " + e.getMessage());
        }
        return 0;
    }

    // Helper method to capitalize the first letter of a string
    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}