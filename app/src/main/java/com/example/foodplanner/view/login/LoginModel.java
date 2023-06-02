package com.example.foodplanner.view.login;

import android.util.Patterns;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel
{
    private FirebaseAuth firebaseAuth;

    public LoginModel() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void loginUser(String email, String password, final LoginListener listener)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task ->
                {
                    if (task.isSuccessful())
                    {
                        String userId = firebaseAuth.getUid();
                        listener.onLoginSuccess(userId);
                    }
                    else
                    {
                        listener.onLoginError(task.getException().getMessage());
                    }
                });
    }

    private boolean isEmailValid(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password)
    {
        return password.length() >= 6;
    }

    public boolean validateCredentials(String email, String password, final LoginListener listener)
    {
        if (!isEmailValid(email))
        {
            listener.onValidationError("Invalid email address");
            return false;
        }
        else if (!isPasswordValid(password))
        {
            listener.onValidationError("Password must be at least 6 characters");
            return false;
        }
        return true;
    }
}
