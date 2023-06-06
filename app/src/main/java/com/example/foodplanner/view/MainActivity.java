package com.example.foodplanner.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodplanner.R;
import com.example.foodplanner.databinding.ActivityMainBinding;
import com.example.foodplanner.models.FirebaseRepository;
import com.example.foodplanner.view.search.SearchFragment;
import com.example.foodplanner.view.signup.SignUpActivity;

public class MainActivity extends AppCompatActivity
{
    ActivityMainBinding binding;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String userID= FirebaseRepository.getInstance(this).getSharedPreferences().getString("userID",null);
        builder = new AlertDialog.Builder(this);

        replaceFragment(new HomeFragment());

        binding.bottomNavigationBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.nav_search:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.nav_favorite:
                    if(userID!=null){
                        replaceFragment(new FavoriteFragment());
                    }else {
                        signupForMore();
                    }
                    break;

                case R.id.nav_profile:
                    if(userID!=null){
                        replaceFragment(new ProfileFragment());
                    }else {
                    signupForMore();
                    }
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void signupForMore() {
        builder.setTitle("Sign up for more features!")
                .setMessage("Save your favorite dishes \n and plan your meals")
                .setCancelable(true)
                .setPositiveButton("Sign up", (dialog, which) ->
                {
                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

}