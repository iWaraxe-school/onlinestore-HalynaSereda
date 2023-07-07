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
