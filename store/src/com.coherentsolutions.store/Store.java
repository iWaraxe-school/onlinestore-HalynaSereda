package com.coherentsolutions.store;
import com.coherentsolutions.domain.Category;
import java.util.*;

public class Store {

    private final List<Category> categoryList;
    private final Set<String> categoryNames;

    public Store() {
        this.categoryList = new ArrayList<>();
        this.categoryNames = new HashSet<>();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void addCategoryToList(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (!categoryNames.add(category.getName())) {
            throw new IllegalArgumentException("Category already exists");
        }
        categoryList.add(category);
    }


    public void addCategory(Category category) {
    }
}
