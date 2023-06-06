package com.example.foodplanner.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.models.FirebaseRepository;
import com.example.foodplanner.presenter.FirebasePresenter;
import com.example.foodplanner.presenter.FirebasePresenterInterface;
import com.example.foodplanner.view.login.LoginActivity;

public class ProfileFragment extends Fragment implements ProfileFragmentInterface {
    AlertDialog.Builder builder;
    FirebasePresenterInterface firebasePresenterInterface;
    private ProgressBar progressBar;

    Button btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar= view.findViewById(R.id.progress_bar);

        firebasePresenterInterface = new FirebasePresenter(this, FirebaseRepository.getInstance(requireContext()));

        builder = new AlertDialog.Builder(requireContext());
        btnLogout=view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v ->
                createLogoutDialog());
    }

    @Override
    public void logoutCurrentUser() {
        firebasePresenterInterface.logoutCurrentUser();
    }

    @Override
    public void onSuccessLogOut() {

        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(requireContext(), StartScreenActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFailureLogOut(Exception exception) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(requireContext(), exception.getMessage(), Toast.LENGTH_LONG).show();

    }

    public void createLogoutDialog() {
        builder.setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setCancelable(true)
                .setPositiveButton("Yes", (dialog, which) -> {
                    progressBar.setVisibility(View.VISIBLE);

                    logoutCurrentUser();
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }
}