package com.example.foodplanner.view.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.presenter.LoginPresenter;
import com.example.foodplanner.presenter.LoginPresenterInterface;
import com.example.foodplanner.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginListener
{
    EditText emailEditText, passwordEditText;
    Button loginBtn;
    ProgressBar loginProgressBar;

    LoginPresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenterInterface = new LoginPresenter(this);

        initializeViews();
        setLoginBtnListener();
    }

    private void initializeViews()
    {
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginBtn = findViewById(R.id.login_button);
        loginProgressBar = findViewById(R.id.login_progress_bar);
    }

    public void setLoginBtnListener()
    {
        loginBtn.setOnClickListener(view ->
        {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if(validateCredentials(email, password))
            {
                loginProgressBar.setVisibility(View.VISIBLE);
                presenterInterface.loginUser(email,password);
            }
        });
    }

    @Override
    public void onValidationError(String message)
    {
        loginProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess(String userId)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor=  sharedPreferences.edit();
        editor.putString("UserID", userId);
        editor.apply();

        loginProgressBar.setVisibility(View.GONE);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onLoginError(String message)
    {
        loginProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean validateCredentials(String email, String password)
    {
        boolean flag = true;
        if(TextUtils.isEmpty(email))
        {
            emailEditText.setError("Enter your email");
            emailEditText.requestFocus();
            flag= false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailEditText.setError("Plz enter a valid email");
            emailEditText.requestFocus();
            flag=false;
        }

        if(TextUtils.isEmpty(password))
        {
            passwordEditText.setError("Enter your password");
            passwordEditText.requestFocus();
            flag= false;
        }
        else if (password.length() < 8)
        {
            passwordEditText.setError("Password must be at least 8 characters");
            passwordEditText.requestFocus();
            flag= false;
        }

        return flag;
    }

}//end class LoginActivity