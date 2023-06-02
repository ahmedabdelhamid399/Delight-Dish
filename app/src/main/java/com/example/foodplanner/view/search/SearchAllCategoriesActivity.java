package com.example.foodplanner.view.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.SimpleMeal;
import com.example.foodplanner.model.search.Category;
import com.example.foodplanner.presenter.AllCategoriesPresenter;
import com.example.foodplanner.presenter.AllIngredientPresenter;
import com.example.foodplanner.presenter.SearchPresenter;
import com.example.foodplanner.view.search.adapter.AreaAdapter;
import com.example.foodplanner.view.search.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class SearchAllCategoriesActivity extends AppCompatActivity implements AllCategoriesActivityInterface {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    CategoryAdapter categoryAdapter;
    SearchView searchView;
    ArrayList<Category> categoryArrayList=new ArrayList<>();
    ArrayList<Category> displayList=new ArrayList<>();
    ImageView closeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all_categories);

        recyclerView=findViewById(R.id.rv_categories);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        categoryAdapter= new CategoryAdapter(this,this,new ArrayList<>());
        recyclerView.setAdapter(categoryAdapter);

        getCategories();
        closeScreen=findViewById(R.id.iv_close_search_by_category);
        closeScreen.setOnClickListener(v -> finish());

        searchView=findViewById(R.id.sv_search_category);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                if (!newText.isEmpty()){
                    displayList.clear();
                    categoryAdapter.setList(displayList);
                    categoryAdapter.notifyDataSetChanged();

                    String search = newText.toLowerCase(Locale.ROOT);
                    for (Category category :categoryArrayList) {
                        if (category.getStrCategory().toLowerCase(Locale.ROOT).startsWith(search)) {
                            displayList.add(category);
                        }
                    }
                    categoryAdapter.setList(displayList);
                    categoryAdapter.notifyDataSetChanged();

                }else{
                    displayList.clear();
                    displayList.addAll(categoryArrayList);
                    categoryAdapter.setList(displayList);
                    categoryAdapter.notifyDataSetChanged();
                }

                return true;
            }
        });
    }

    @Override
    public void getCategories() {
        AllCategoriesPresenter.getAllCategories(this);
    }

    @Override
    public void onSuccessResult(ArrayList<Category> categories) {

        categoryArrayList.addAll(categories);
        displayList.addAll(categories);
        categoryAdapter.setList(displayList);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailureResult(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        Log.e("=====TAG=====", "onFailureResult: "+ error );
    }

    @Override
    public void navigateToParticularCategoryMeals(String categoryName) {
        Intent intent = new Intent(this, ParticularCategoryMealsActivity.class);
        intent.putExtra("categoryName",categoryName);
        startActivity(intent);

    }
}