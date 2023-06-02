package com.example.foodplanner.presenter;

import com.example.foodplanner.view.login.LoginActivity;
import com.example.foodplanner.view.login.LoginListener;
import com.example.foodplanner.view.login.LoginModel;

public class LoginPresenter implements LoginPresenterInterface, LoginListener
{
    LoginModel loginModel;
    LoginListener loginListener;

    public LoginPresenter(LoginListener Listener)
    {
        loginModel = new LoginModel();
        loginListener = Listener;
    }

    @Override
    public void onLoginSuccess(String userId)
    {
        loginListener.onLoginSuccess(userId);
    }

    @Override
    public void onLoginError(String message)
    {
        loginListener.onLoginError(message);
    }

    @Override
    public void loginUser(String email, String password)
    {
        loginModel.loginUser(email, password, loginListener);
    }

    @Override
    public void onValidationError(String message)
    {

    }
}
