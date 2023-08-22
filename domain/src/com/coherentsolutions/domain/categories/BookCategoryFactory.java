package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.store.CategoryFactory;

/**
 * Factory class for creating instances of BookCategory.
 */
public class BookCategoryFactory implements CategoryFactory {

    /**
     * Creates and returns an instance of BookCategory.
     *
     * @return An instance of BookCategory.
     */
    @Override
    public Category createCategory() {
        return new BookCategory();
    }
}