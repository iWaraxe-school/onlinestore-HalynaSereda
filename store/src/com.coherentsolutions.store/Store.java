package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a store containing categories of products.
 */
public class Store {
    private static Store instance; // Singleton instance
    private final List<Category> categoryList;

    private Store() {
        this.categoryList = new ArrayList<>();
    }


    /**
     * Gets the singleton instance of the Store class.
     *
     * @return The singleton instance of the Store class.
     */
    public static synchronized Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    /**
     * Gets the list of categories in the store.
     *
     * @return The list of categories in the store.
     */
    public List<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * Adds a category to the store's list of categories.
     *
     * @param category The category to add to the store.
     */
    public void addCategoryToList(Category category) {
        categoryList.add(category);
    }

    /**
     * Returns a string representation of the store and its categories.
     *
     * @return A string representation of the store and its categories.
     */
    @Override
    public String toString() {
        StringBuilder storeStr = new StringBuilder("Store: \n");
        for (Category category : categoryList) {
            storeStr.append(category.toString()).append("\n");
        }
        return storeStr.toString();
    }
}

