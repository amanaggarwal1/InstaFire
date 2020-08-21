package com.amanaggarwal1.instafire.models;

import com.amanaggarwal1.instafire.R;
import com.google.firebase.firestore.PropertyName;



public class User {
    public String username;
    public String email;
    public long phone_number;
    public String user_Id;

    public User(){

    }

    public User(String username, String email, long phone_number, String user_Id) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.user_Id = user_Id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phone_number +
                ", userId='" + user_Id + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }
}
