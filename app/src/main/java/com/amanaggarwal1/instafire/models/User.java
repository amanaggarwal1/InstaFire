package com.amanaggarwal1.instafire.models;

import com.amanaggarwal1.instafire.R;
import com.google.firebase.firestore.PropertyName;



public class User {
    public String username;
    public String email;
    @PropertyName("phone_number")
    public long phoneNumber;
    @PropertyName("user_id")
    public String userId;

    public User(){

    }

    public User(String username, String email, long phoneNumber, String userId) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", userId='" + userId + '\'' +
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
