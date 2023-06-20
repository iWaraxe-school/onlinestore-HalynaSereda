package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.Categories;
import com.coherentsolutions.domain.Category;

public class FoodCategory extends Category {

    public FoodCategory (){
        super(String.valueOf(Categories.FOOD));
    }

}