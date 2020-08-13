package com.amanaggarwal1.instafire.models;
import androidx.annotation.NonNull;
import com.google.firebase.firestore.PropertyName;

public class Post {
    public String description = "User";
    public long creation_time_ms = 0;
    public String image_url = "";
    public User user;
}
