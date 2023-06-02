package com.example.foodplanner.view;

import com.example.foodplanner.model.detailedmeal.DetailedMeal;


import java.util.List;

public interface FavoriteFragmentInterface {
    public void showData(List<DetailedMeal> meals);
    public void showDataFailed(String error);
    public void removeMeal(DetailedMeal meal);

}
