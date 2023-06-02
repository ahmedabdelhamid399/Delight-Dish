package com.example.foodplanner.presenter;

public interface FirebasePresenterInterface
{
    public void registerUser(String userName,String email,String password,String confirmPassword);
    public void logoutCurrentUser();
}
