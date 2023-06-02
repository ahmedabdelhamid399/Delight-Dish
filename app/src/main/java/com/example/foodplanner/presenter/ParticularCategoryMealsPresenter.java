package com.example.foodplanner.presenter;

import com.example.foodplanner.model.SimpleMeal;
import com.example.foodplanner.model.search.ParticularCategoryMealsRepository;
import com.example.foodplanner.view.search.ParticularCategoryMealsActivityInterface;

import java.util.ArrayList;

public class ParticularCategoryMealsPresenter {
    private  static ParticularCategoryMealsActivityInterface particularCategoryMealsActivityInterface;

    public static void getParticularCategoryMeals(String categoryName,ParticularCategoryMealsActivityInterface particularCategoryMealsInterface){
        particularCategoryMealsActivityInterface=particularCategoryMealsInterface;
        ParticularCategoryMealsRepository.getParticularCategoryMeals(categoryName);
    }
    public static void onSuccessResult(ArrayList<SimpleMeal> meals){
        particularCategoryMealsActivityInterface.onSuccessResult(meals);
    }
    public static void onFailureResult(String error){
        particularCategoryMealsActivityInterface.onFailureResult(error);
    }
}
