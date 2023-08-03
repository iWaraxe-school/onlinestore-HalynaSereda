package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private final List<Category> categoryList;

    public Store() {
        this.categoryList = new ArrayList<>();

    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void addCategoryToList(Category category) {
       categoryList.add(category);
    }



    @Override
    public String toString() {
        StringBuilder storeStr = new StringBuilder("Store: \n");
        for(Category category : categoryList) {
            storeStr.append(category.toString()).append("\n");
        }
        return storeStr.toString();
    }


}
