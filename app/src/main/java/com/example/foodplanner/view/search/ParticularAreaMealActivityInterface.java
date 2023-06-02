package com.example.foodplanner.view.search;

import com.example.foodplanner.model.SimpleMeal;

import java.util.ArrayList;

public interface ParticularAreaMealActivityInterface {
    public void getParticularAreaMeals(String areaName);
    public void onSuccessResult(ArrayList<SimpleMeal> meals);
    public void onFailureResult(String error);
    public void navigateToViewDetails(String position);
}
