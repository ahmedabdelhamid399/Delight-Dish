package com.example.foodplanner.view.search;

import com.example.foodplanner.model.search.Category;
import java.util.ArrayList;

public interface AllCategoriesActivityInterface {
    public void getCategories();
    public void onSuccessResult(ArrayList<Category> categories);
    public void onFailureResult(String error);
    public void navigateToParticularCategoryMeals(String categoryName);
}
