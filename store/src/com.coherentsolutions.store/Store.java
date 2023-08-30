package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton class representing an online store.
 */
public class Store {
    private static volatile Store instance; // Singleton instance
    private final List<Category> categoryList;

    private Store() {
        this.categoryList = new ArrayList<>();
    }

    public static Store getInstance() {
        if (instance == null) {
            synchronized (Store.class) {
                if (instance == null) {
                    instance = new Store();
                }
            }
        }
        return instance;
    }

    public List<Category> getCategoryList() {
        synchronized (categoryList) {
            return Collections.unmodifiableList(categoryList);
        }
    }

    public void addCategoryToList(Category category) {
        synchronized (categoryList) {
            categoryList.add(category);
        }
    }

    @Override
    public String toString() {
        StringBuilder storeStr = new StringBuilder("Store: \n");
        synchronized (categoryList) {
            for (Category category : categoryList) {
                storeStr.append(category.toString()).append("\n");
            }
        }
        return storeStr.toString();
    }
}
