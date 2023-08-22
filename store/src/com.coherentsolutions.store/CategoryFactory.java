package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;

/**
 * Interface for the CategoryFactory, which defines the method to create categories.
 */
public interface CategoryFactory {

    /**
     * Creates and returns a new instance of a category.
     *
     * @return A new category instance.
     */
    Category createCategory();
}