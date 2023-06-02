package com.example.foodplanner.model;

public class SignupUser
{
    private String userName;
    private String email;
    private String password;
    private String confirmPassword;

    public SignupUser(String userName, String email, String password, String confirmPassword)
    {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }


    public void setUserName(String displayName) {
        this.userName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;}
}
