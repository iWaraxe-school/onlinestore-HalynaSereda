package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.store.CategoryFactory;


/**
 * Factory class for creating instances of FoodCategory.
 */
public class FoodCategoryFactory implements CategoryFactory {

    /**
     * Creates and returns an instance of FoodCategory.
     *
     * @return An instance of FoodCategory.
     */
    @Override
    public Category createCategory() {
        return new FoodCategory();
    }
}