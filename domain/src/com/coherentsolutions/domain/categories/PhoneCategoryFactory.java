package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.store.CategoryFactory;

public class PhoneCategoryFactory implements CategoryFactory {
    @Override
    public Category createCategory() {
        return new FoodCategory();
    }
}