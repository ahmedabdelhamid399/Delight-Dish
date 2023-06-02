package com.example.foodplanner.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.ActivityMainBinding;
import com.example.foodplanner.view.search.SearchFragment;

public class MainActivity extends AppCompatActivity
{
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        changeFragment(new HomeFragment());

        binding.bottomNavigationBar.setOnItemSelectedListener(item ->
        {
            switch (item.getItemId())
            {
                case R.id.nav_home:
                    changeFragment(new HomeFragment());
                    break;

                case R.id.nav_search:
                    changeFragment(new SearchFragment());
                    break;

                case R.id.nav_favorite:
                    changeFragment(new FavouriteFragment());
                    break;

                case R.id.nav_profile:
                    changeFragment(new ProfileFragment());
                    break;
            }
            return false;
        });
    }

    private void changeFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}