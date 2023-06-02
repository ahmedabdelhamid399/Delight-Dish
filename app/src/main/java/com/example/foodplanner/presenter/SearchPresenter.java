package com.example.foodplanner.presenter;

import com.example.foodplanner.model.search.AllAreas;
import com.example.foodplanner.model.search.Area;

import java.util.List;

public class SearchPresenter {
public List<Area> getAreas(){
    return AllAreas.getInstance().getAllAreas();
}


}
