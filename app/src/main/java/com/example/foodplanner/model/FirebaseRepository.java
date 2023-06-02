package com.example.foodplanner.model;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.foodplanner.network.LogOutResult;
import com.example.foodplanner.network.SignUpResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FirebaseRepository implements FirebaseRepositoryInterface {
    private Context context;
    private FirebaseAuth mAuth;
    private  SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String PREFS_NAME="my_preferences";

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    private static FirebaseRepository repo = null;

    public static FirebaseRepository getInstance(Context _context) {
        if (repo == null) {
            repo = new FirebaseRepository(_context);
        }

        return repo;
    }

    private FirebaseRepository(Context _context) {
        context=_context;
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    @Override
    public void registerUser(SignUpResult signUpResult, SignupUser signupUser) {

        mAuth.createUserWithEmailAndPassword(signupUser.getEmail(), signupUser.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null)
                        {
                            String userID = user.getUid();
                            editor.putString("userID", userID);
                            editor.commit();
                            signUpResult.onSuccessRegistration();
                        }
                    }
                    else
                    {
                        signUpResult.onFailureRegistration(task);


                    }
                });

    }

    @Override
    public void logoutCurrentUser(LogOutResult logOutResult) {
        try {
            mAuth.signOut();
            editor.putString("userID", null);
            editor.commit();
            logOutResult.onSuccessLogOut();
        }catch (Exception exception){
            logOutResult.onFailureLogOut(exception);
        }


    }


}
