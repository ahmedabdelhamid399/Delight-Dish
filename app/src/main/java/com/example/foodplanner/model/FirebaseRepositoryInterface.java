package com.example.foodplanner.model;


import com.example.foodplanner.network.LogOutResult;
import com.example.foodplanner.network.SignUpResult;

public interface FirebaseRepositoryInterface {
    public void registerUser(SignUpResult signUpResult, SignupUser signupUser);
    public void logoutCurrentUser(LogOutResult logOutResult);



}
