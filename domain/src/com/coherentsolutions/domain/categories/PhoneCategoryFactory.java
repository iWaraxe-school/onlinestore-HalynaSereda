package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.store.CategoryFactory;

/**
 * Factory class for creating instances of PhoneCategory.
 */
public class PhoneCategoryFactory implements CategoryFactory {

    /**
     * Creates and returns an instance of PhoneCategory.
     *
     * @return An instance of PhoneCategory.
     */
    @Override
    public Category createCategory() {
        return new PhoneCategory();
    }
}