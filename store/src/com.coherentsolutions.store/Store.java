package com.coherentsolutions.store;
import com.coherentsolutions.domain.Category;

import java.util.List;
import java.util.ArrayList;
public class Store {

    private final List<Category> categoryList;

    public Store () {
        this.categoryList = new ArrayList<Category>();
    }

    public List<Category> getCategoryList() {
        return categoryList;}
    public void addCategoryToList(Category category) {
        categoryList.add(category);
    }
    }
