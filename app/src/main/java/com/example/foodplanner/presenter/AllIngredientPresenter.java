package com.example.foodplanner.presenter;

import com.example.foodplanner.model.search.AllIngredientsRepository;
import com.example.foodplanner.model.search.Ingredient;
import com.example.foodplanner.view.search.AllIngredientsActivityInterface;

import java.util.ArrayList;

public class AllIngredientPresenter {
    private  static AllIngredientsActivityInterface allIngredientsActivityInterface;

    public static void getAllIngredients(AllIngredientsActivityInterface allIngredientsInterface){
        allIngredientsActivityInterface= allIngredientsInterface;
        AllIngredientsRepository.getAllIngredients();
    }
    public static void onSuccessResult(ArrayList<Ingredient> ingredients){
        allIngredientsActivityInterface.onSuccessResult(ingredients);
    }
    public static void onFailureResult(String error){
        allIngredientsActivityInterface.onFailureResult(error);
    }
}
