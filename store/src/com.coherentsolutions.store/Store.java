package com.coherentsolutions.store;
import com.coherentsolutions.domain.Category;

import java.util.List;
import java.util.ArrayList;
import  java.util.HashSet;
import java.util.Set;
public class Store {

    private final List<Category> categoryList;

    public Store () {
        this.categoryList = new ArrayList<Category>();
    }

    public List<Category> getCategoryList() {
        return categoryList;}
    public void addCategoryToList(Category category) {
        categoryList.add(category);
        String[] category1 = new String[]{};
        Set<String> set = new HashSet<String>();
        for (String element : category1) {
            if (!set.add(element)) {}
        }
        if (category == null) {
            System.out.println("Category can not be null " );
        }
    }
}

